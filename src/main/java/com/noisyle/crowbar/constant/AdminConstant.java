package com.noisyle.crowbar.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.noisyle.crowbar.core.util.JSONUtils;

public class AdminConstant {
	public static enum Role {
		ADMIN("admin", "管理员"),
		USER("user", "用户");
		
		private String id;
		private String text;
		
		private Role(String id, String text){
			this.id = id;
			this.text = text;
		}
		
		public String getId(){
			return this.id;
		}
		
		public String getText(){
			return this.text;
		}
		
		public static String getText(String id){
			String text = null;
			if(id != null){
				id = id.trim();
				for(Role role:Role.values()){
					if(role.id.equals(id)){
						text = role.text;
						break;
					}
				}
			}
			return text;
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
				map.put("id", role.id);
				map.put("text", role.text);
				list.add(map);
			}
			return JSONUtils.toJSON(list);
		}
	}
	
}
