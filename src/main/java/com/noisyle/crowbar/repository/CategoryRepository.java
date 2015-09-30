package com.noisyle.crowbar.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.noisyle.crowbar.core.base.BaseMongoRepository;
import com.noisyle.crowbar.model.Category;

@Repository
public class CategoryRepository extends BaseMongoRepository<Category, String> {
	
	public List<Category> querCategoryList(String q) {
		Query query = null;
		if(q!=null && !"".equals(q.trim())){
			query = Query.query(Criteria.where("categoryName").regex(q.trim()));
		}
		return mongoTemplate.find(query, Category.class);
	}
}
