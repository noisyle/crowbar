package com.noisyle.crowbar.repository;

import org.springframework.stereotype.Repository;

import com.noisyle.crowbar.core.base.BaseMongoRepository;
import com.noisyle.crowbar.model.Article;

@Repository
public class ArticleRepository extends BaseMongoRepository<Article, String> {
	
}
