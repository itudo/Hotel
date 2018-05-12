package com.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;


/** 
 *注册表操作帮助类
 */
public class RegistrationUtils {
	//添加 name a
	//   pwd  a
	public static void saveRegistration(Map<String,String> map) throws BackingStoreException{
		        // systemNodeForPackage();
				// //保存在[HKEY_LOCAL_MACHINE\SOFTWARE\JAVASOFT\Prefs]
				// userNodeForPackage() ; //保存在
				// [HKEY_CURRENT_USER\SOFTWARE\JAVASOFT\PREFS] 推荐 将信息存在用户节点下
		Preferences pre=Preferences.userNodeForPackage(RegistrationUtils.class);
		if(map!=null){
			Set<String> keys=map.keySet();  //取出map中所有的键
			Iterator<String> its=keys.iterator();//键是一个set  智能取出Iterator迭代器
			while(its.hasNext()){  //循环迭代器
				String key=its.next();  //取出一个键
				String value=map.get(key);//根据键从map中取出值
				//向注册表写入
				pre.put(key, value);
				
			}
			pre.flush();  //流操作  ：flush
		}
	}
	
	//删除
	public static void delRegistration(Map<String,String> map) throws BackingStoreException{
		Preferences pre=Preferences.userNodeForPackage(RegistrationUtils.class);
		if(map!=null){
			Set<String> keys=map.keySet();  //取出map中所有的键
			Iterator<String> its=keys.iterator();//键是一个set  智能取出Iterator迭代器
			while(its.hasNext()){  //循环迭代器
				String key=its.next();  //取出一个键
				pre.remove(key);  //remove（）根据键来删除
			}
			pre.flush();
		}
	}
	
	
	//查询  返回null表示没有哈尔个注册表       返回map表示有这个注册表箱，并已经在map填充好了值
	//形参数map中存在了要查找的注册表的键
	public static Map findRegistration(){
		Map<String,String>map=null;
		Preferences pre=Preferences.userNodeForPackage(RegistrationUtils.class);
        String name=pre.get("name", "");   //get("键"，"默认的值")； 如果这个键对应的值存在  则返回这个值 如果不存在  则返回一个空指针
        if(!name.equals("")){
        	map=new HashMap<String,String>();
        	map.put("name", pre.get("name", ""));   //pre.get("name","") 从注册表取  取出来存在map中
        	map.put("pwd",pre.get("pwd", ""));
        }
        return map;
	}
	public static void main(String[] args) throws BackingStoreException {
		Map<String,String> map=new HashMap<String,String>();
		map.put("name", "smith");
		map.put("pwd", "a");
		saveRegistration( map);
		//delRegistration(map);
		//Map m=findRegistration();
		//System.out.println(m);
	}
}


