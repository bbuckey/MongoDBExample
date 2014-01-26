package org.mongo.util;

import java.util.Properties;

import com.mongodb.DBAddress;
import org.mongo.entity.*;
import org.mongo.manager.*;
import java.util.List;
import org.mongo.dao.*;
import org.mongo.util.StringUtils;

public class MongoDBMainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.setProperty("host", "127.0.0.1");
		props.setProperty("port", String.valueOf(DBAddress.defaultPort()));
		props.setProperty("connectionPerHost", String.valueOf(1)); 
		props.setProperty("connectTimeout", String.valueOf(100000));
		TestDao testDao;
		TestDataManager tdm;
		MongoDataSource dba;
		TestDataEnitity tde;
		try{
			
			dba = new MongoDataSource(new DBAddress(DBAddress.defaultHost() + ":" + String.valueOf(DBAddress.defaultPort()) + "/test"));
			testDao = new TestDao(dba);
			tdm = new TestDataManager(testDao);
			tde = new TestDataEnitity();
			System.err.println(StringUtils.getAnnotationValueForFieldofClass(tde.getClass(), "id", "key"));
			System.err.println(StringUtils.getAnnotationValueForClass(testDao.getClass(), "collectionName"));
			//TestDataEnitity tde = tdm.findOneDocumentByKeyValue("x", 100);
			/*List<TestDataEnitity> l = tdm.getTestData("");
			System.err.println(l.size());
			for(TestDataEnitity tde : l){
				System.err.println(tde.getId().toStringMongod());
				System.err.println(tde.getH());
				System.err.println(tde.getX());
			}*/
			//System.err.println(tde.getId().toStringMongod());
			//System.err.println(tde.getH());
			//System.err.println(tde.getX());
		}
		catch(Throwable t){
			t.printStackTrace();
		}
		
	}

}
