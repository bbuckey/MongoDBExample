package org.mongo.util;

import java.util.Properties;

import com.mongodb.DBAddress;
import org.mongo.entity.*;
import org.mongo.manager.*;
import java.util.List;
import org.mongo.dao.*;

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
		try{
			testDao = new TestDao(props);
			tdm = new TestDataManager(testDao);
			List<TestDataEnitity> l = tdm.getTestData("");
			System.err.println(l.size());
			for(TestDataEnitity tde : l){
				System.err.println(tde.getId().toStringMongod());
				System.err.println(tde.getH());
				System.err.println(tde.getX());
			}
			
		}
		catch(Throwable t){
			t.printStackTrace();
		}
		
	}

}
