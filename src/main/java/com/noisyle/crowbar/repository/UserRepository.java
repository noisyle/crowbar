package com.noisyle.crowbar.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.noisyle.crowbar.core.base.BaseMongoRepository;
import com.noisyle.crowbar.model.User;

@Repository
public class UserRepository extends BaseMongoRepository<User, String> {
	
	public User getUserByLoginName(String loginname) {
		User user = null;
		if(loginname!=null && !"".equals(loginname.trim())){
			Criteria criteria = Criteria.where("loginname").is(loginname);
			Query query = new Query(criteria);
			user = mongoTemplate.findOne(query, User.class);
		}
		return user;
	}
	
	public void removeAll() {
		mongoTemplate.dropCollection(User.class);
	}
}
