package org.mongo.dao;

import java.util.List;
import java.util.Map;

import com.mongodb.DBObject;

public interface IBaseDao {
	public void removeDBObjectFromCollection(DBObject dbo) throws Exception;
	public void addDBObjectToCollection(DBObject dbo) throws Exception;
	public DBObject findOneObjectFromKeyValue(String key, Object value);
	public DBObject findOneObjectFromDBObject(DBObject obj);
	public List<DBObject> findObjectsFromKeyValueMap(Map m);
	public List<DBObject> findObjectsFromDBOjects(DBObject obj);
	public List<DBObject> getCollectionAsList();
	public void removeDBObjectFromCollection(String key, Object value)
			throws Exception ;
}
