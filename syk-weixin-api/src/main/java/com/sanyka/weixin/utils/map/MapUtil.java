package com.sanyka.weixin.utils.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MapUtil {
	
	
	/**
	 * @param map
	 * @param key
	 * @return
	 * 适用场景：从所有的页面中找出某个菜单下的页面（页面中储存的是map对象）
	 */
	public static List getLikeKey(Map map , String key){
		List<Object> value = new ArrayList<Object>();
		 List<String> keyList = new ArrayList<String>();
		   Set set  = (Set) map.keySet();
		   Iterator it=set.iterator();
		   while (it.hasNext()) {
			   String string =(String)it.next();
		    //通过排序后,key是有序的.
		    if (string.indexOf(key) != -1) {
		     keyList.add(string);
		     value.add(map.get(string));
		    } else if (string.indexOf(key) == -1 && keyList.size() == 0) {
		     //当不包含这个key时而且key.size()等于0时,说明还没找到对应的key的开始
		     continue;
		    } else {
		     //当不包含这个key时而且key.size()大于0时,说明对应的key到当前这个key已经结束.不必要在往下找
		     break;
		    }
		   }
		   keyList.clear();
		   keyList=null;
		   return value;
	}
	
	
	//从页面的map对象中查找所有的pannels 
	
	//----菜单（叶节点）  --pagelist  循环{pannelslist 循环{pannel} }

}
