package com.noisyle.crowbar.task;

import org.springframework.stereotype.Component;

import com.noisyle.crowbar.core.base.BaseAutoTask;

@Component
public class Task1 extends BaseAutoTask {
	@Override
	public void run() {
		logger.debug("Task1启动，参数：{}", this.parameter);

        logger.debug("Task1结束");
	}
}
