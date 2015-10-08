package com.noisyle.crowbar.core.task;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

public class DaemonTask {

	private static Logger logger = LoggerFactory.getLogger(DaemonTask.class);
	Map<String, ThreadPoolTaskScheduler> schedulerHolder = new HashMap<>();
	
	public void run() {
		logger.debug("守护线程启动");
		
		if(!schedulerHolder.containsKey("task1")){
			ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
			scheduler.initialize();
			Trigger trigger = new CronTrigger("*/30 * * * * ?");
			try {
				Runnable task1 = (Runnable) Class.forName("com.noisyle.crowbar.task.Task1").newInstance();
				scheduler.schedule(task1, trigger);
				schedulerHolder.put("task1", scheduler);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				logger.error("实例化task实例失败", e);
			}
		}
		
        logger.debug("守护线程结束");
	}
}
