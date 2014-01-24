package org.mongo.dao;

import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBConnector;
import com.mongodb.DBTCPConnector;
import com.mongodb.DBAddress;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;

import javax.net.SocketFactory;

public class MongoDataSource {

	Mongo mongo;
	DBAddress dbAddress;
	DBConnector dbConnector;
	DB db;
	
	MongoDataSource(){;}
	
	public MongoDataSource(Properties props) throws UnknownHostException{
		SocketFactory sf = new MongoSocketFactory();
		MongoOptions mo = new MongoOptions();
		ServerAddress sa = new ServerAddress(DBAddress.defaultHost(), DBAddress.defaultPort());
		mo.setSocketFactory(sf);
		mo.setAutoConnectRetry(false);
		mo.setConnectionsPerHost(1);
		mo.setConnectTimeout(100000);
		dbAddress = new DBAddress(DBAddress.defaultHost() + ":"+ String.valueOf(DBAddress.defaultPort()) + "/test");
		//mongo = new Mongo(sa,mo);
		//dbConnector = mongo.getConnector(); //new DBTCPConnector(mongo,sa);
		db = Mongo.connect(dbAddress);///mongo.getDB("test");
		mongo = db.getMongo();
		dbConnector = mongo.getConnector();
	}
	
	public void setDBConnecter(DBConnector _dbConnector){
		this.dbConnector = _dbConnector;
	}
	
	public void setDBAddress(DBAddress _dbAddress){
		this.dbAddress = _dbAddress;
	}
	
	public void setDB(DB _db){
		this.db = _db;
	}
	
	public DBConnector getDBConnecter(){
		return this.dbConnector;
	}
	
	public DBAddress getDBAddress(){
		return this.dbAddress;
	}
	
	public DB getDB(){
		return this.db;
	}
	
	public boolean ConnectToTheDBWithUser(String username, char[] passwd){
		boolean isAuth = db.authenticate(username, passwd);
		return isAuth;
	}
	
	public DB getDBbyName(String name){
		return mongo.getDB(name);
	}
	
	public DBCollection getDBCollectionWithDataBaseAndCollectionName(String dataBase, String collectionName){
		return this.mongo.getDB(dataBase).getCollectionFromString(collectionName);
	}
	
	public DBCollection getDBCollectionWithDataBaseAndCollectionName(String dataBase, org.mongo.atomic.CollectionNames collectionName){
		return this.mongo.getDB(dataBase).getCollectionFromString(collectionName.getCamelCase());
	}
	
}
