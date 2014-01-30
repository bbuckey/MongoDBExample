package org.mongo.dao;

import java.util.List;
import java.util.Map;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public interface IBaseDao {
	public DBCollection getDatabaseCollection();

	public void removeDBObjectFromCollection(Map map) throws Exception;

	public void removeDBObjectFromCollection(DBObject dbo) throws Exception;

	public void addDBObjectToCollection(DBObject dbo) throws Exception;

	public void addDBObjectToCollection(Map map) throws Exception;

	public DBObject findOneObject(String key, Object value);

	public DBObject findOneObject(DBObject dbo);

	public DBObject findOneObject(Map map);

	public List<DBObject> findObjects(Map m);

	public List<DBObject> findObjects(DBObject dbo);

	public List<DBObject> getCollectionAsList();

	public void removeDBObjectFromCollection(String key, Object value)
			throws Exception;

	/**
	 * This method updates a document, this is done by a query object and a
	 * updated object the query object can be the exact same object as the
	 * updated object minues the modification
	 * 
	 * @param query
	 *            the original object
	 * @param updateObject
	 *            the updated object
	 */
	public void updateDocumentWithDBObject(DBObject query, DBObject updateObject);
}
