package com.ns.Util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson 转对象，集合工具类
 * @author 李安
 *
 * @param <T>
 */
public class JacksonUtil<T> {
	static ObjectMapper mapper = new ObjectMapper(); 
	/*** 
	  * 转对象 
	  * @param josn 
	  * @param clz 
	  * @return 
	  */
	 public static<T> T jsonConverObject(Object NeedTojosn, Class<T> clz) 
	 { 
	  T t = null; 
	  try
	  { 
	   String josn= mapper.writeValueAsString(NeedTojosn);
	   t = mapper.readValue(josn, clz); 
	  } catch (JsonParseException e) 
	  { 
	   e.printStackTrace(); 
	  } catch (JsonMappingException e) 
	  { 
	   e.printStackTrace(); 
	  } catch (IOException e) 
	  { 
	   e.printStackTrace(); 
	  } 
	  return t; 
	 } 
	
	 public static<T> List<T> jsonConverList(Object NeedTojosn, Class<T> clz) throws JsonProcessingException 
	 { 
	  List<T> me = null; 
	  try
	  { 
	   // jacksonMapper 
	   // .disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES); 
	   // jacksonMapper.enableDefaultTyping(); 
	   // jacksonMapper.setVisibility(JsonMethod.FIELD,JsonAutoDetect.Visibility.ANY); 
	   // jacksonMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, 
	   // false);//格式化 
	   // jacksonMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL); 
	   // jacksonMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, 
	   // false); 
	   String josn= mapper.writeValueAsString(NeedTojosn);
	   JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clz);// clz.selGenType().getClass() 
	   me = mapper.readValue(josn, javaType); 
	  } catch (JsonParseException e) 
	  { 
	   e.printStackTrace(); 
	  } catch (JsonMappingException e) 
	  { 
	   e.printStackTrace(); 
	  } catch (IOException e) 
	  { 
	   e.printStackTrace(); 
	  } 
	  return me; 
	 } 
	
	 
	 public void testTo(){
		 
		 
	 }
	 
}
