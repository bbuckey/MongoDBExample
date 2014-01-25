package org.mongo.manager;

import org.mongo.dao.TestDao;

import java.lang.reflect.Method;
import java.util.List;
import com.mongodb.DBObject;
import org.mongo.entity.TestDataEnitity;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import org.mongo.util.StringUtils;


public class TestDataManager implements ITestDataManager{
 
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
	public void addDocumentToCollection(TestDataEnitity tde)throws Exception{
		DBObject obj = new BasicDBObject();
		obj.put("h", tde.getH());
		obj.put("x", tde.getX());
		this.testDao.addDBObjectToCollection(obj);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TestDataEnitity findOneDocumentByKeyValue(String key, Object value){
		DBObject obj = this.testDao.findOneObjectFromKeyValue("x", 100);
		TestDataEnitity tde = new TestDataEnitity();
		tde.setId((ObjectId)obj.get("_id"));
		tde.setX((Double)obj.get("x"));
		tde.setH((String)obj.get("h"));
		for(String s : obj.keySet()){
			Class cl = TestDataEnitity.class.getClass();
			try{
			Method m = cl.getMethod("set"+StringUtils.makeFirstLetterUpperCase(s),null);
			tde = (TestDataEnitity)m.invoke(tde,obj.get(s));
			}catch(Throwable t){
				t.printStackTrace();
			}
			
		}
		return tde;
	}

	
}
