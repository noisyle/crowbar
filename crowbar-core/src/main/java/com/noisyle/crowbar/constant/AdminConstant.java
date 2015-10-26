package com.noisyle.crowbar.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.noisyle.crowbar.core.util.JSONUtils;

public class AdminConstant {
	public static final String SESSION_KEY_USER_CONTEXT = "userContext";
	public static final String SESSION_KEY_CONTEXT_ROOT = "ctx";
	
	public static enum Role {
		ADMIN("管理员"),
		USER("用户");
		
		private String text;
		
		private Role(String text){
			this.text = text;
		}
		
		public String getText(){
			return this.text;
		}
		
		public static Role get(String str){
			Role role = null;
			if(str != null){
				role = Role.valueOf(str);
			}
			return role;
		}
		
		public static String getJSONString(int extra){
			List<Map<String, Object>> list = new ArrayList<>();
			Map<String, Object> map;
			if(extra==1){
				map = new HashMap<>();
				map.put("id", "");
				map.put("text", "");
				list.add(map);
			}else if(extra==2){
				map = new HashMap<>();
				map.put("id", "-1");
				map.put("text", "");
				list.add(map);
			}
			for(Role role:Role.values()){
				map = new HashMap<>();
				map.put("id", role.toString());
				map.put("text", role.text);
				list.add(map);
			}
			return JSONUtils.toJSON(list);
		}
	}
	
}
