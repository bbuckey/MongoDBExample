package org.mongo.manager;

import org.mongo.dao.TestDao;
import java.util.List;
import com.mongodb.DBObject;
import org.mongo.entity.TestDataEnitity;
import org.bson.types.ObjectId;


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
		List<DBObject> l = this.testDao.getTestDataCollecton();
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
	
}
