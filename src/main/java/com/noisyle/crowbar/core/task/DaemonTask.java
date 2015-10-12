package com.noisyle.crowbar.core.task;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.noisyle.crowbar.core.base.BaseAutoTask;
import com.noisyle.crowbar.core.base.ITask;
import com.noisyle.crowbar.repository.AutoTaskRepository;

public class DaemonTask {

	private static Logger logger = LoggerFactory.getLogger(DaemonTask.class);
	private static final Map<String, ThreadPoolTaskScheduler> schedulerHolder = new HashMap<>();
	
	@Autowired
	private AutoTaskRepository autoTaskRepository;
	
	public void run() {
		logger.debug("守护线程启动");
		Map<String, ITask> tasks = autoTaskRepository.querAutoTaskMap();
		for(String id : schedulerHolder.keySet()){
			if(!tasks.keySet().contains(id) || !tasks.get(id).getEnable()){
				schedulerHolder.get(id).destroy();
				schedulerHolder.remove(id);
			}
		}
		for(ITask task : tasks.values()){
			if(task.getEnable()){
				if(!schedulerHolder.containsKey(task.getId())){
					ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
					scheduler.initialize();
					Trigger trigger = new CronTrigger(task.getCron());
					try {
						BaseAutoTask instance = (BaseAutoTask) Class.forName(task.getClazz()).newInstance();
						instance.setParameter(task.getParameter());
						scheduler.schedule(instance, trigger);
						schedulerHolder.put(task.getId(), scheduler);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
						logger.error("自动任务实例化失败,name:{},class:{}", task.getTaskName(), task.getClazz(), e);
					}
				}
			}
		}
        logger.debug("守护线程结束");
	}
}
