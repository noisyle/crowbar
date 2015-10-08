package com.noisyle.crowbar.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task1 implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(Task1.class);

	@Override
	public void run() {
		logger.debug("Task1启动");

        logger.debug("Task1结束");
	}
}
