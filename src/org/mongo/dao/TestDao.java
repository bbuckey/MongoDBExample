package org.mongo.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mongo.atomic.CollectionNames;
import org.mongo.dao.MongoDataBase;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class TestDao extends MongoDataBase {

	DBCollection dbc;
	
	public TestDao(Properties props) throws UnknownHostException{super(props); dbc = super.getDBCollection(CollectionNames.TESTDATA);}
	
	public List getTestDataCollecton(){
		List l = new ArrayList();
		DBCursor cursor = dbc.find();
		DBObject dbo;
		while (cursor.hasNext()){
			 dbo = cursor.next();
			 l.add(dbo);
		}
		return l;
	}
	
}
