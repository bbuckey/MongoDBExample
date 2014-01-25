package org.mongo.dao;

import java.net.UnknownHostException;
import java.util.Properties;
import com.mongodb.Mongo;
import org.mongo.dao.MongoDataSource;
import com.mongodb.QueryOperators;
import com.mongodb.DBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.QueryBuilder;
import org.mongo.atomic.CollectionNames;
import com.mongodb.DBEncoder;
import com.mongodb.DefaultDBDecoder;
import com.mongodb.DefaultDBEncoder;
import org.bson.BasicBSONEncoder;

public abstract class MongoDataBase {
	private MongoDataSource mds;
	
	public MongoDataBase(){;}
	
	public MongoDataBase(Properties props) throws UnknownHostException{
		mds = new MongoDataSource(props);
		if(!mds.getDBConnecter().isOpen()){
			mds.setDB(Mongo.connect(mds.getDBAddress()));
			mds.setDBConnecter(mds.getDB().getMongo().getConnector());
		}
	}
	
	protected MongoDataSource getMongoDataSource(){
		return mds;
	}
	
	protected DBCollection getDBCollectionOrCreateNewCollection(String collectionName){
		DBCollection dbc;
		dbc = mds.getDB().getCollection(collectionName);
		return dbc;
	}
	
	protected DBCollection getDBCollection(String collectionName){
		DBCollection dbc;
		dbc = mds.getDB().getCollectionFromString(collectionName);
		return dbc;
	}
	
	protected DBCollection getDBCollection(CollectionNames collectionName){
		DBCollection dbc;
		dbc = mds.getDB().getCollectionFromString(collectionName.getCamelCase());
		return dbc;
	}
	
	protected DBCollection getDBCollectionFromDataBaseAndCollectionName(String dbname, String collectionName){
		return mds.getDBCollectionWithDataBaseAndCollectionName(dbname, collectionName);
	}
	
	protected DBCollection getDBCollectionFromDataBaseAndCollectionName(String dbname, CollectionNames collectionName){
		return mds.getDBCollectionWithDataBaseAndCollectionName(dbname, collectionName);
	}

}


