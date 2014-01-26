package org.mongo.manager;

import org.mongo.dao.TestDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.DBObject;

import org.mongo.entity.BaseEntity;
import org.mongo.entity.TestDataEnitity;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import org.mongo.util.StringUtils;
import org.mongo.util.AnnotationUtils;

public class TestDataManager extends BaseManager{
 
	TestDao testDao;	
	
	public TestDataManager(){;}
	
	public TestDataManager(TestDao _testDao){
		testDao = _testDao;
	}
	
	public TestDao getTestDao(){
		return this.testDao;
	}
	
	public void setTestDao(TestDao _testDao){
		this.testDao = _testDao;
	}
	
	@Override
	public List getCollectionDocumentsAsList(){
		List collList = new java.util.ArrayList();
		List<DBObject> l = this.testDao.getCollectionAsList();
		for(DBObject j : l){
				TestDataEnitity tde = new TestDataEnitity();
			for(String s : j.keySet()){
				if(s.equalsIgnoreCase("_id")){
					tde.setId((ObjectId)j.get(s));
				}
				if(s.equalsIgnoreCase("name")){
					tde.setH((String)j.get(s));
				}
				if(s.equalsIgnoreCase("h")){
					tde.setH((String)j.get(s));
				}
				if(s.equalsIgnoreCase("x")){
					tde.setX((Double)j.get(s));
				}
			}
			collList.add(tde);
			
		}
		return collList;
	}
	
	@Override
	public void addDocumentToCollection(BaseEntity tde)throws Exception{
		Map map = new HashMap();
		for (Field f : tde.getClazz().getDeclaredFields()){
			String fieldName = f.toString();
			fieldName = fieldName.substring(fieldName.lastIndexOf(".")+1);
			String dbFieldName = AnnotationUtils.getAnnotationValueForFieldofClass(tde.getClazz(),fieldName, "key");
			if(dbFieldName != null && !dbFieldName.isEmpty() && !"_id".equalsIgnoreCase(dbFieldName)){
				fieldName = "get"+StringUtils.makeFirstLetterUpperCase(fieldName);
				map.put(dbFieldName, tde.getClazz().getMethod(fieldName).invoke(tde));
			}
		}
		if(!map.isEmpty()){
			DBObject obj = new BasicDBObject();
			obj.putAll(map);
			this.testDao.addDBObjectToCollection(obj);
		}
	}
	
	@Override
	public TestDataEnitity findOneDocumentByKeyValue(String key, Object value){
		DBObject obj = this.testDao.findOneObjectFromKeyValue("x", 100);
		TestDataEnitity tde = new TestDataEnitity();
		for(String s : obj.keySet()){
			Class cl = TestDataEnitity.class.getClass();
			try{
			Method m = cl.getMethod("set"+StringUtils.makeFirstLetterUpperCase(s));
			m.invoke(tde,obj.get(s));
			}catch(Throwable t){
				t.printStackTrace();
			}
		}
		return tde;
	}

	@Override
	public void addDocumentToCollection(Map m) throws Exception {
		if(!m.isEmpty()){
			DBObject obj = new BasicDBObject();
			obj.putAll(m);
			this.testDao.addDBObjectToCollection(obj);
		}
	}
	
}
