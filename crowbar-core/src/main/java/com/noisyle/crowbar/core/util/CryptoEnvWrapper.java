package com.noisyle.crowbar.core.util;

import org.springframework.core.env.Environment;

public class CryptoEnvWrapper {
	private Environment env;

	public CryptoEnvWrapper(Environment env) {
		this.env = env;
	}

	public String getProperty(String key) {
		return CryptoUtils.decipher(env.getProperty(key));
	}
}
