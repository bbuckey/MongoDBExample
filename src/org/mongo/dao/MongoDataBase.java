package org.mongo.dao;

import java.net.UnknownHostException;
import java.util.Properties;
import com.mongodb.Mongo;
import org.mongo.dao.MongoDataSource;

import com.mongodb.DBAddress;
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
	private MongoDataSource mongoDataSource;

	protected MongoDataBase() {
		;
	}

	public MongoDataBase(DBAddress _dbAddress) throws UnknownHostException {
		mongoDataSource = new MongoDataSource(_dbAddress);
	}

	public MongoDataBase(Mongo _mongo, String dbName)
			throws UnknownHostException {
		mongoDataSource = new MongoDataSource(_mongo, dbName);
	}

	public MongoDataBase(MongoDataSource _mds) {
		mongoDataSource = _mds;
	}

	protected void setMangoDataSource(MongoDataSource _mds) {
		this.mongoDataSource = _mds;
	}

	public MongoDataSource getMongoDataSource() {
		return mongoDataSource;
	}

	protected DBCollection getDBCollectionOrCreateNewCollection(
			String collectionName) {
		DBCollection dbc;
		dbc = mongoDataSource.getDB().getCollection(collectionName);
		return dbc;
	}

	protected DBCollection getDBCollection(String collectionName) {
		DBCollection dbc;
		dbc = mongoDataSource.getDB().getCollectionFromString(collectionName);
		return dbc;
	}

	protected DBCollection getDBCollection(CollectionNames collectionName) {
		DBCollection dbc;
		dbc = mongoDataSource.getDB().getCollectionFromString(
				collectionName.getCamelCase());
		return dbc;
	}

	protected DBCollection getDBCollectionFromDataBaseAndCollectionName(
			String dbname, String collectionName) {
		return mongoDataSource.getDBCollectionWithDataBaseAndCollectionName(
				dbname, collectionName);
	}

	protected DBCollection getDBCollectionFromDataBaseAndCollectionName(
			String dbname, CollectionNames collectionName) {
		return mongoDataSource.getDBCollectionWithDataBaseAndCollectionName(
				dbname, collectionName);
	}

}
