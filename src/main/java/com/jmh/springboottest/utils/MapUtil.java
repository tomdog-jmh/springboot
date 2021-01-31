package com.jmh.springboottest.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * name : jmh
 * time : 2020/9/8 16:53
 */
public class MapUtil<T> {
    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new LinkedHashMap<>();
        Class<?> clazz = obj.getClass();
        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                map.put(fieldName, value);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }


    /**
     * 将map值存入obj
     * @param t 对象
     * @param map 目标map
     * @return
     */
    public static <T> T mapToObj(T t, Map<String,String> map)  {
        Class clazz = t.getClass();

        for (String key:map.keySet()) {
            try{
                Field f = clazz.getDeclaredField(key);
                f.setAccessible(true);
                f.set(t,map.get(key));
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return t;
    }

    /**
     * 判断对象属性值是否为空,为空给一个默认值
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T objIsNull(T t){
        Class<?> clazz = t.getClass();
        //得到所有属性
        Field[] fields = clazz.getDeclaredFields();

        for (Field field:fields) {
            try{
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                //获取属性值
                Object value = field.get(t);
                //如果为空,填充值
                if (value == null || value.equals("")){
                    Field declaredField = clazz.getDeclaredField(name);
                    declaredField.setAccessible(true);
                    declaredField.set(t,null);
                }
            }catch (Exception e){
                System.out.println(e);
            }

        }
        return t;
    }
}
