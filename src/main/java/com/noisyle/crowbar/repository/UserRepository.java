package com.noisyle.crowbar.repository;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSFile;
import com.noisyle.crowbar.core.base.BaseMongoRepository;
import com.noisyle.crowbar.core.exception.GeneralException;
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

	public GridFSFile uploadAvatar(MultipartFile file) {
		try {
			InputStream is = file.getInputStream();
			String filename = file.getOriginalFilename();
			String contentType = file.getContentType();
			DBObject metadata = new BasicDBObject();
			metadata.put("type", "avatar");
			return gridFsTemplate.store(is, filename, contentType, metadata);
		} catch (IOException e) {
			throw new GeneralException("上传失败", e);
		}
	}
}
