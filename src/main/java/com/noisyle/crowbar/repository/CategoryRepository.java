package com.noisyle.crowbar.repository;

import org.springframework.stereotype.Repository;

import com.noisyle.crowbar.core.base.BaseMongoRepository;
import com.noisyle.crowbar.model.Category;

@Repository
public class CategoryRepository extends BaseMongoRepository<Category, String> {
	
}
