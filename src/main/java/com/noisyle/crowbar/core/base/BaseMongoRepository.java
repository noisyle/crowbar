package com.noisyle.crowbar.core.base;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.noisyle.crowbar.core.pagination.Page;
import com.noisyle.crowbar.core.pagination.PageParam;
import com.noisyle.crowbar.core.util.ReflectionUtils;

public class BaseMongoRepository<T extends BaseModel, ID extends Serializable> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	protected Class<T> clazz;
	protected static final String ID_KEY = "_id";

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

	public void delete(ID id) {
		mongoTemplate.remove(findById(id));
	}

	public void delete(T entity) {
		mongoTemplate.remove(entity);
	}

	public List<T> findAll() {
		return mongoTemplate.findAll(clazz);
	}

	public Page<T> getPage(PageParam pageParam) {
		Query query = new Query(); // TODO Set query parameters
		return getPage(pageParam, query);
	}
	
	public Page<T> getPage(PageParam pageParam, Query query) {
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
	
}