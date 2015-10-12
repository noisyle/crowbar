package com.noisyle.crowbar.task;

import com.noisyle.crowbar.core.base.BaseAutoTask;

public class Task1 extends BaseAutoTask {
	@Override
	public void run() {
		logger.debug("Task1启动，参数：{}", this.parameter);

        logger.debug("Task1结束");
	}
}
