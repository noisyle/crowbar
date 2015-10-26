package com.noisyle.crowbar.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noisyle.crowbar.core.base.BaseMongoRepository;
import com.noisyle.crowbar.core.base.ITask;
import com.noisyle.crowbar.model.AutoTask;

@Repository
public class AutoTaskRepository extends BaseMongoRepository<AutoTask, String> {
	
	public Map<String, ITask> querAutoTaskMap() {
		Map<String, ITask> map = new HashMap<String, ITask>();
		List<AutoTask> list = findAll();
		for(AutoTask task : list){
			map.put(task.getId(), task);
		}
		return map;
	}
}
