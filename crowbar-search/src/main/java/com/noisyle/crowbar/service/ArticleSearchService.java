package com.noisyle.crowbar.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import com.noisyle.crowbar.model.Article;
import com.noisyle.crowbar.repository.ArticleRepository;


@Service
public class ArticleSearchService implements InitializingBean {
	@Autowired
	private ArticleRepository articleRepository;
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private Directory directory;
	private Analyzer analyzer;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("开始生成索引");
		Date start_time = new Date();
		List<Article> articles = articleRepository.findAll();
		directory = new RAMDirectory();
		analyzer = new MMSegAnalyzer();
		IndexWriter writer = new IndexWriter(directory, new IndexWriterConfig(analyzer));
		for (Article article : articles) {
			Document doc = new Document();
			doc.add(new TextField("title", article.getTitle(), Field.Store.YES));
			doc.add(new StringField("id", article.getId(), Field.Store.YES));
			doc.add(new TextField("subtitle", article.getSubtitle(), Field.Store.YES));
			doc.add(new StringField("content", article.getContent(), Field.Store.YES));
			writer.addDocument(doc);
		}
		writer.close(); // 关闭读写器
		
		Date end_time = new Date();
		logger.info("生成索引完成，耗时:{}毫秒", end_time.getTime()-start_time.getTime());
	}

	public List<Article> searchArticles(String q) {
		List<Article> result = new LinkedList<Article>();
		try {
			IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(directory));
			QueryParser parser = new QueryParser("title", analyzer);
			Query query = parser.parse(q);

			TopDocs topDocs = searcher.search(query, 100);
			ScoreDoc[] hits = topDocs.scoreDocs;
			logger.debug("关键词【"+q+"】查询到"+hits.length+"条");
			
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = searcher.doc(hits[i].doc);
				Article article = new Article();
				article.setId(hitDoc.get("id"));
				article.setTitle(hitDoc.get("title"));
				result.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
