package com.noisyle.crowbar.core.base;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.noisyle.crowbar.core.datatables.FormatHandler;
import com.noisyle.crowbar.core.datatables.FormatedPage;
import com.noisyle.crowbar.core.datatables.Page;
import com.noisyle.crowbar.core.datatables.PageParam;
import com.noisyle.crowbar.core.util.ReflectionUtils;

public class BaseMongoRepository<T extends BaseModel, ID extends Serializable> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	@Autowired
	protected GridFsTemplate gridFsTemplate;
	
	protected Class<T> clazz;
	protected static final String ID_KEY = "id";

	/**
	 * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型Class.
	 * eg. public class UserRepository extends BaseMongoRepository<User>
	 */
	public BaseMongoRepository() {
		this.clazz = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用BaseMongoRepository的构造函数. 在构造函数中定义对象类型Class.
	 * eg. UserRepository<User> userRepository = new BaseMongoRepository<User>(mongoTemplate, User.class);
	 */
	public BaseMongoRepository(final MongoTemplate mongoTemplate, final Class<T> clazz) {
		this.mongoTemplate = mongoTemplate;
		this.clazz = clazz;
	}

	public <S extends T> S save(S entity) {
		if("".equals(entity.id)){
			entity.setId(null);
		}
		mongoTemplate.save(entity);
		return entity;
	}

	public T findById(ID id) {
		return mongoTemplate.findById(id, clazz);
	}

	public boolean exists(ID id) {
		return mongoTemplate.exists(Query.query(Criteria.where(ID_KEY).is(id)), clazz);
	}

	public long count() {
		return mongoTemplate.count(new Query(), clazz);
	}

	public void delete(String id) {
		mongoTemplate.remove(Query.query(Criteria.where(ID_KEY).is(id)), clazz);
	}

	public void delete(T entity) {
		mongoTemplate.remove(entity);
	}

	public List<T> findAll() {
		return mongoTemplate.findAll(clazz);
	}

	public Page<T> getPage(PageParam pageParam) {
		Query query = new Query();
		// 处理过滤查询条件
		if(pageParam.getSearch() !=null && pageParam.getSearch().getValue()!=null && !"".equals(pageParam.getSearch().getValue().trim())){
			List<Criteria> criterias = new LinkedList<Criteria>();
			for(int i=0;i<pageParam.getColumns().length;i++){
				if(pageParam.getColumns()[i].getData()!=null && pageParam.getColumns()[i].getData().indexOf(".")<0){
					criterias.add(Criteria.where(pageParam.getColumns()[i].getData())
						.regex(pageParam.getSearch().getValue()));
				}
			}
			query.addCriteria(new Criteria().orOperator(criterias.toArray(new Criteria[criterias.size()])));
		}
		
		// 处理排序
		if(pageParam.getOrder()!=null && pageParam.getOrder().length>0){
			Sort.Order[] orders = new Sort.Order[pageParam.getOrder().length];
			for(int i=0;i<pageParam.getOrder().length;i++){
				orders[i] = new Sort.Order(
					Sort.Direction.fromString(pageParam.getOrder()[i].getDir()),
					pageParam.getColumns()[pageParam.getOrder()[i].getColumn()].getData()
				);
			}
			query.with(new Sort(orders));
		}
		return getPage(pageParam, query);
	}
	
	public Page<T> getPage(PageParam pageParam, Query query) {
		// 进行分页查询
		long total = mongoTemplate.count(query, clazz);
		int skip = pageParam.getStart();
		int limit = pageParam.getLength();
		query.skip(skip).limit(limit);
		List<T> data = mongoTemplate.find(query, clazz);
		Page<T> Page = new Page<T>();
		Page.setData(data);
		Page.setRecordsTotal(total);
		Page.setRecordsFiltered(total);
		Page.setDraw(pageParam.getDraw());
		return Page;
	}
	
	public FormatedPage getFormatedPage(PageParam pageParam) {
		// 分页查询结果格式化
		return FormatHandler.handle(getPage(pageParam), pageParam);
	}
}