package org.mongo.manager;

import org.mongo.dao.TestDao;
import java.util.List;
import com.mongodb.DBObject;
import org.mongo.entity.TestDataEnitity;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;


public class TestDataManager {
 
	TestDao testDao;	
	TestDataManager(){
		;
	}
	public TestDataManager(TestDao _testDao){
		testDao = _testDao;
	}
	
	
	public List getTestData(String CollectionName){
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
	
	public void addTestDataToCollection(TestDataEnitity tde)throws Exception{
		DBObject obj = new BasicDBObject();
		obj.put("h", tde.getH());
		obj.put("x", tde.getX());
		this.testDao.addDBObjectToCollection(obj);
	}
	
	
	public TestDataEnitity findOneTestDataDocumentByKeyValue(String key, Object value){
		DBObject obj = this.testDao.findOneObjectFromKeyValue("x", 100);
		TestDataEnitity tde = new TestDataEnitity();
		tde.setId((ObjectId)obj.get("_id"));
		tde.setX((Double)obj.get("x"));
		tde.setH((String)obj.get("h"));
		//for(String s : obj.keySet()){
			//ClassLoader cl = getClass().getClassLoader();
			
		//}
		return tde;
	}
	
}
