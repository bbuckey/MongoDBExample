package org.mongo.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.mongo.atomic.CollectionNames;
import org.mongo.dao.MongoDataBase;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.mongodb.MongoException;

public class TestDao extends MongoDataBase implements IBaseDao{

	DBCollection dbc;

	public TestDao(Properties props) throws UnknownHostException {
		super(props);
		dbc = super.getDBCollection(CollectionNames.TESTDATA);
	}

	@Override
	public DBObject findOneObjectFromKeyValue(String key, Object value){
		DBObject dbo = new BasicDBObject();
		dbo.put(key, value);
		return this.dbc.findOne(dbo);
	}
	
	@Override
	public void addDBObjectToCollection(DBObject dbo) throws Exception {
		WriteResult wr = this.dbc.save(dbo);
		// this.dbc.insert(dbo);
		if (wr.getError() != null && !wr.getError().equals("")) {
			throw new MongoException("Error while inserting a document"
					+ wr.getError());
		}
		this.dbc.save(dbo);

	}

	@Override
	public void removeDBObjectFromCollection(String key, Object value)
			throws Exception {
		DBObject dbo = this.dbc.findOne((new BasicDBObject()).put(key, value));
		if (dbo != null && dbo.containsField(key) && dbo.get(key).equals(value)) {
			removeDBObjectFromCollection(dbo);
		} else {
			throw new MongoException("Error while removing a document: The document was not found");
		}

	}

	@Override
	public void removeDBObjectFromCollection(DBObject dbo) throws Exception {
		WriteResult wr = this.dbc.remove(dbo);
		if (wr.getError() != null && !wr.getError().equals("")) {
			throw new MongoException("Error while removing a document"
					+ wr.getError());
		}
	}

	@Override
	public DBObject findOneObjectFromDBObject(DBObject obj) {
		
		return this.dbc.findOne(obj);
	}

	@Override
	public List<DBObject> findObjectsFromKeyValueMap(Map m) {
		DBObject dbo = new BasicDBObject();
		dbo.putAll(m);
		DBCursor cursor = this.dbc.find(dbo);
		return cursor!= null ? cursor.toArray() : null;
	}

	@Override
	public List<DBObject> findObjectsFromDBOjects(DBObject obj) {
		DBCursor cursor = this.dbc.find(obj);
		return cursor!= null ? cursor.toArray() : null;
	}

	@Override
	public List<DBObject> getCollectionAsList() {
		DBCursor cursor = dbc.find();
		return cursor!= null ? cursor.toArray() : null;
	}

}
