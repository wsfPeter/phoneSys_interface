package com.net.toooen.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings({ "rawtypes", "unchecked","unused" })
public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public static void main(String[] args) {
        String sss = "[{\"friendid\":\"-1\",\"jmobile\":\"13367241851\",\"remark\":\"123\"},{\"friendid\":\"-2\",\"jmobile\":\"13367241852\",\"remark\":\"123\"},{\"friendid\":\"-3\",\"jmobile\":\"13367241853\",\"remark\":\"123\"},{\"friendid\":\"-4\",\"jmobile\":\"13367241854\",\"remark\":\"123\"},{\"friendid\":\"-5\",\"jmobile\":\"13367241855\",\"remark\":\"123\"}]";
        log.info(sss);
        // JSONObject jsonObj = JSONObject.fromObject(sss);
        JSONArray jsonArray = JSONArray.fromObject(sss);
        log.info(jsonArray.toString());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jo = (JSONObject) jsonArray.get(i);
            log.info("--> " + jo);
        }
    }

    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     * 
     * @param jsonString
     * @param pojoCalss
     * @return
     */
    public static Object getObject4JsonString(String jsonString, Class pojoCalss) {
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        pojo = JSONObject.toBean(jsonObject, pojoCalss);
        return pojo;
    }

    /**
     * 从json HASH表达式中获取一个map，改map支持嵌套功能
     * 
     * @param jsonString
     * @return
     */
    public static Map getMap4Json(String jsonString) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Iterator keyIter = jsonObject.keys();
        String key;
        Object value;
        Map valueMap = new HashMap();

        while (keyIter.hasNext()) {
            key = (String) keyIter.next();
            value = jsonObject.get(key);
            valueMap.put(key, value);
        }
        return valueMap;
    }

    /**
     * 从json数组中得到相应java数组
     * 
     * @param jsonString
     * @return
     */
    public static Object[] getObjectArray4Json(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
    }

    /**
     * 从json对象集合表达式中得到一个java对象列表
     * 
     * @param jsonString
     * @param pojoClass
     * @return
     */
    public static List getList4Json(String jsonString, Class pojoClass) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;

        List list = new ArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject, pojoClass);
            list.add(pojoValue);
        }
        return list;
    }

    /**
     * 从json数组中解析出java字符串数组
     * 
     * @param jsonString
     * @return
     */
    public static String[] getStringArray4Json(String jsonString) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            stringArray[i] = jsonArray.getString(i);
        }
        return stringArray;
    }

    /**
     * 从json数组中解析出javaLong型对象数组
     * 
     * @param jsonString
     * @return
     */
    public static Long[] getLongArray4Json(String jsonString) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            longArray[i] = jsonArray.getLong(i);

        }
        return longArray;
    }

    /**
     * 从json数组中解析出java Integer型对象数组
     * 
     * @param jsonString
     * @return
     */
    public static Integer[] getIntegerArray4Json(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            integerArray[i] = jsonArray.getInt(i);
        }
        return integerArray;
    }

    /**
     * 从json数组中解析出java Date 型对象数组，使用本方法必须保证
     * 
     * @param jsonString
     * @return
     */
    public static Date[] getDateArray4Json(String jsonString, String DataFormat) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Date[] dateArray = new Date[jsonArray.size()];
        String dateString;
        Date date;
        for (int i = 0; i < jsonArray.size(); i++) {
            dateString = jsonArray.getString(i);
            try {
                date = new SimpleDateFormat(DataFormat).parse(dateString);
                dateArray[i] = date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // date = DateUtil.strForDate(dateString, DataFormat);
        }
        return dateArray;
    }

    /**
     * 从json数组中解析出java Integer型对象数组
     * 
     * @param jsonString
     * @return
     */
    public static Double[] getDoubleArray4Json(String jsonString) {

        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Double[] doubleArray = new Double[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            doubleArray[i] = jsonArray.getDouble(i);

        }
        return doubleArray;
    }

    /**
     * 将java对象转换成json字符串
     * 
     * @param javaObj
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj) {

        JSONObject json;
        json = JSONObject.fromObject(javaObj);
        return json.toString();
    }

    /**
     * 将java对象转换成json字符串,并设定日期格式
     * 
     * @param javaObj
     * @param dataFormat
     * @return
     */
    // public static String getJsonString4JavaPOJO(Object javaObj, String
    // dataFormat) {
    //
    // JSONObject json;
    // JsonConfig jsonConfig = configJson(dataFormat);
    // json = JSONObject.fromObject(javaObj, jsonConfig);
    // return json.toString();
    // }

    // /**
    // * JSON 时间解析器具
    // *
    // * @param datePattern
    // * @return
    // */
    // public static JsonConfig configJson(String datePattern) {
    // JsonConfig jsonConfig = new JsonConfig();
    // jsonConfig.setExcludes(new String[] { "" });
    // jsonConfig.setIgnoreDefaultExcludes(false);
    // jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
    // jsonConfig.registerJsonValueProcessor(Date.class, new
    // DateJsonValueProcessor(datePattern));
    //
    // return jsonConfig;
    // }

    /**
     * 
     * @param excludes
     * @param datePattern
     * @return
     */
    // public static JsonConfig configJson(String[] excludes, String
    // datePattern) {
    // JsonConfig jsonConfig = new JsonConfig();
    // jsonConfig.setExcludes(excludes);
    // jsonConfig.setIgnoreDefaultExcludes(false);
    // jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
    // jsonConfig.registerJsonValueProcessor(Date.class, new
    // DateJsonValueProcessor(datePattern));
    //
    // return jsonConfig;
    // }

    /**
     * 将JSONObject 转换为 Map
     * 
     * @param json
     * @return
     * @author yuanhuaihao company huilet 2013-5-6
     */
    public static HashMap<String, Object> getMapByJsonObject(JSONObject json) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Set<?> keys = json.keySet();
        for (Object key : keys) {
            Object o = json.get(key);
            if (o instanceof JSONArray)
                map.put((String) key, getListByJsonArray((JSONArray) o));
            else if (o instanceof JSONObject)
                map.put((String) key, getMapByJsonObject((JSONObject) o));
            else
                map.put((String) key, o);
        }
        return map;
    }

    /**
     * 将JSONArray 转换为 List
     * 
     * @param json
     * @return
     * @author yuanhuaihao company huilet 2013-5-6
     */
    public static List getListByJsonArray(JSONArray json) {
        List<Object> list = new ArrayList<Object>();
        for (Object o : json) {
            if (o instanceof JSONArray)
                list.add(getListByJsonArray((JSONArray) o));
            else if (o instanceof JSONObject)
                list.add(getMapByJsonObject((JSONObject) o));
            else
                list.add(o);
        }
        return list;
    }

    /**
     * List 转化为JSONArray
     * 
     * @param list
     * @return
     * @author yuanhuaihao company huilet 2013-5-6
     */
    public static JSONArray getJsonArrayByList(List list) {
        JSONArray obj = new JSONArray();
        // log.info(JSONArray.fromObject(list));
        return JSONArray.fromObject(list);
    }

    /**
     * Map 转化为JSONObject
     * 
     * @see getJsonObjByMap2
     * @param map
     * @return
     * @author yuanhuaihao company huilet 2013-5-6
     */
    public static JSONObject getJsonObjByMap(Map map) {
        return JSONObject.fromObject(map);
    }

    /**
     * List 转化为JSONArray
     */
    public static JSONArray getJsonArrayByList2(List<String> list) {
        return JSONArray.fromObject(list);
    }

    /**
     * 注意时间.
     */
    public static JSONObject getJsonObjByMap2(Map<String, String> map) {
        return JSONObject.fromObject(map);
    }

    /***
     * 将record中的数据转换为map .
     */
    public static Map<String, Object> getMapByJfinalRecord2(Record rc) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] names = rc.getcolumnNames();
        for (String name : names) {
            map.put(name.toLowerCase(), rc.get(name) == null ? "" : rc.get(name));
        }
        return map;
    }

    public static JSONArray getJsonObjByjfinalList2(List<Record> rec) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Record r : rec) {
            list.add(getMapByJfinalRecord2DateTime(r, SystemUtil.yyyy_MM_dd_HH_mm_ss));
        }
        return getJsonArrayByList(list);
    }

    public static JSONArray getJsonObjByjfinalList2(List<Record> rec, String datePattern) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Record r : rec) {
            list.add(getMapByJfinalRecord2DateTime(r, datePattern));
        }
        return getJsonArrayByList(list);
    }

    /***
     * record 按 fromate 时间格式返回.
     */
    public static Map<String, Object> getMapByJfinalRecord2DateTime(Record rc, String fromate) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] names = rc.getcolumnNames();
        for (String name : names) {
            if (rc.get(name) != null) {
                if (rc.get(name) instanceof java.util.Date
                        || rc.get(name) instanceof java.sql.Timestamp
                        || rc.get(name) instanceof java.sql.Date) {
                    try {
                        map.put(name.toLowerCase(),
                                new SimpleDateFormat(fromate).format(rc.get(name)));
                    } catch (Exception e) {
                        log.warn("transfer date error " + rc.get(name));
                        map.put(name.toLowerCase(), "");
                    }
                } else {
                    map.put(name.toLowerCase(), rc.get(name) == null ? "" : rc.get(name));
                }
            } else {
                map.put(name.toLowerCase(), "");
            }
        }
        return map;
    }

    /***
     * record 按默认时间格式返回.self define 时间格式
     * <P>
     * Map<String,String> dateFormates = new HashMap<String,String>();
     * <P>
     * dateFormates.put("birth", SystemUtil.yyyy_MM_dd);
     * <P>
     * dateFormates.put("loginDate", SystemUtil.yyyy_MM_dd_HH_mm_ss);
     * <P>
     * dateFormates.put("createDate", SystemUtil.yyyy_MM_dd_HH_mm_ss);
     */
    public static Map<String, Object> getMapByJfinalRecord2DateTime(Record rc,
            Map<String, String> dateFormates) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] names = rc.getcolumnNames();
        for (String name : names) {
            String namelow = name.toLowerCase();
            if (rc.get(name) != null) {
                if (dateFormates != null && dateFormates.containsKey(namelow)) {
                    try {
                        map.put(namelow,
                                new SimpleDateFormat(dateFormates.get(namelow)).format(rc.get(name))
                                        + "");
                    } catch (Exception e) {
                        log.warn(
                                dateFormates.get(namelow) + " transfer *.Date error "
                                        + rc.get(name), e);
                        map.put(namelow, "");
                    }
                } else {
                    map.put(namelow, rc.get(name) == null ? "" : rc.get(name));
                }
            } else {
                map.put(name.toLowerCase(), "");
            }
        }
        return map;
    }

    /***
     * record 按默认时间格式返回. java.sql.Timestamp , & java.util.Date -- >
     * yyyy_MM_dd_HH_mm_ss java.sql.Date -- > yyyy_MM_dd
     */
    public static Map<String, Object> getMapByJfinalRecord2DateTime(Record rc) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] names = rc.getcolumnNames();
        for (String name : names) {
            if (rc.get(name) != null) {
                if (rc.get(name) instanceof java.util.Date) {
                    try {
                        map.put(name.toLowerCase(), new SimpleDateFormat(
                                SystemUtil.yyyy_MM_dd_HH_mm_ss).format(rc.get(name)));
                    } catch (Exception e) {
                        log.warn("transfer java.util.Date error " + rc.get(name));
                        map.put(name.toLowerCase(), "");
                    }
                } else if (rc.get(name) instanceof java.sql.Timestamp) {
                    try {
                        map.put(name.toLowerCase(), new SimpleDateFormat(
                                SystemUtil.yyyy_MM_dd_HH_mm_ss).format(rc.get(name)));
                    } catch (Exception e) {
                        log.warn("transfer date error " + rc.get(name));
                        map.put(name.toLowerCase(), "");
                    }
                } else if (rc.get(name) instanceof java.sql.Date) {
                    try {
                        map.put(name.toLowerCase(),
                                new SimpleDateFormat(SystemUtil.yyyy_MM_dd).format(rc.get(name)));
                    } catch (Exception e) {
                        log.warn("transfer date error " + rc.get(name));
                        map.put(name.toLowerCase(), "");
                    }
                } else {
                    map.put(name.toLowerCase(), rc.get(name) == null ? "" : rc.get(name));
                }
            } else {
                map.put(name.toLowerCase(), "");
            }
        }
        return map;
    }

    public static HashMap<String, Object> getMapByJfinalRecord(Record rc) {
        HashMap map = new HashMap();
        String[] names = rc.getcolumnNames();
        for (String name : names) {
            // 1.5 Ken 改为小写
            map.put(name.toLowerCase(), rc.get(name) == null ? "" : rc.get(name));
        }
        return map;
    }

    public static JSONArray getJsonObjByjfinalList(List<Record> rec) {
        List<HashMap> list = new ArrayList<HashMap>();
        for (Record r : rec) {
            list.add(getMapByJfinalRecord(r));
        }
        return getJsonArrayByList(list);
    }

    public static JSONArray getJsonObjByjfinalList(List<Record> rec, String datePattern) {
        List<HashMap> list = new ArrayList<HashMap>();
        for (Record r : rec) {
            list.add(getMapByJfinalRecord(r, datePattern));
        }
        return getJsonArrayByList(list);
    }

    public static HashMap getMapByJfinalRecord(Record rc, String p) {
        HashMap map = new HashMap();
        String[] names = rc.getcolumnNames();
        for (String name : names) {
            if (rc.get(name) != null) {
                if (rc.get(name) instanceof Date) {
                    map.put(name.toLowerCase(), new SimpleDateFormat(p).format(rc.get(name)));
                } else {
                    map.put(name.toLowerCase(), rc.get(name));
                }
            }
        }

        return map;
    }
}
