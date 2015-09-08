package com.noisyle.crowbar.core.base;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.WriteResult;
import com.noisyle.crowbar.core.util.ReflectionUtils;

public class BaseMongoRepository<T extends BaseModel> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	protected Class<T> clazz;

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
	
	public void save(T model) {
		mongoTemplate.save(model);
	}
	
	public WriteResult remove(T model) {
		return mongoTemplate.remove(model);
	}

	public T get(String id) {
		Criteria criteria = Criteria.where("id").in(id);
		Query query = new Query(criteria);
		return mongoTemplate.findOne(query, clazz);
	}

	public List<T> findAll() {
		return mongoTemplate.find(new Query(), clazz);
	}

	public List<T> find(Map<String, Object> params){
		// TODO
		return null;
	}

	public int count(Map<String, Object> params) {
		// TODO
		return 1;
	}
}