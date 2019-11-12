package com.st.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by admin on 2016/8/3.
 */
public class StringUtils {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);
    /**
     * 判断字符串是否为空 如空返回True,否则返回false
     * @param object
     * @return
     */
    public static boolean isNull(Object object)
    {

        if(null==object||"".equals(object)||"null".equals(object)||"undefined".equals(object))
            return true;
        else
            return false;
    }

    /**
     * 每隔一定长度，取一个字符
     * @param tokenStr
     * @param interval
     * @return
     */
    public static  String getStrByInterval(String tokenStr,int interval){
        //如果token字符串为空则返回空字符串
        if(StringUtils.isNull(tokenStr)){
            return "";
        }
        //声明存储
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<tokenStr.length();i++){
            //如果能被5整除
            if((i+1)%interval==0){
                sb.append(tokenStr.charAt(i));
            }

        }
        return sb.toString();
    }
    /**
     * map对象转换为json字符串
     * @param strMap
     * @return
     */
    public static String mapToJsonStr(Map strMap){
        //map to json
        ObjectMapper mapper = new ObjectMapper();
        String jsonMapStr=null;
        try {
            jsonMapStr = mapper.writeValueAsString(strMap);
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }

        return jsonMapStr;
    }

    /**
     * json字符串转换为map
     * @param jsonStr
     * @throws IOException
     */
    public static Map<String,String> jsonStrToMap(String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> paramMap = null;
        try {
            paramMap = mapper.readValue(jsonStr,Map.class);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return paramMap;
    }
    /**
     * 字符串解码
     * @param paramStr
     * @return
     */
    public static String decodeString(String paramStr){

        try {
            return URLDecoder.decode(paramStr,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return paramStr;
    }
    /**
     * 字符串解码
     * @param paramStr
     * @return
     */
    public static String doubleDecodeString(String paramStr){
        //进行2次解码
       String  decodeOnce = decodeString(paramStr);
        //然后返回
        return decodeString(decodeOnce);
    }
    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            logger.info("request URI::{}",request.getRequestURI());
            logger.info("request parameter:::::::::::::name:{},value:{}",name,value);
            returnMap.put(name, value);
            //处理分页参数
            if("startPage".equals(name)||"pageSize".equals(name)){
                    returnMap.put(name,Integer.valueOf(value));
            }
        }
        return returnMap;
    }

    public static Map<String,Object> jsonStrToObjMap(String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> paramMap = null;
        try {
            paramMap = mapper.readValue(jsonStr,Map.class);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return paramMap;
    }
}
