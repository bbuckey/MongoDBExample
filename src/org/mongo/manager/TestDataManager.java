package org.mongo.manager;

import org.mongo.dao.TestDao;
import java.util.List;
import com.mongodb.DBObject;
import org.mongo.entity.TestDataEnitity;

public class TestDataManager {
 
	TestDao testDao;	
	TestDataManager(){
		;
	}
	TestDataManager(TestDao _testDao){
		testDao = _testDao;
	}
	
	
	public List getTestData(String CollectionName){
		List collList = new java.util.ArrayList();
		List<DBObject> l = this.testDao.getTestDataCollecton();
		for(DBObject j : l){
				TestDataEnitity tde = new TestDataEnitity();
				tde.setId((String)j.get("_id"));
				tde.setX((Integer)j.get("x"));
				tde.setH((String)j.get("h"));
				collList.add(tde);
		}
		return collList;
	}
	
}
