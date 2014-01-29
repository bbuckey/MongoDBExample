package org.mongo.dao;

import java.util.List;
import java.util.Map;
import org.mongo.dao.MongoDataBase;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.mongodb.MongoException;
import org.mongo.annotation.Collection;
import org.mongo.annotation.CollectionName;
import org.mongo.util.AnnotationUtils;

@Collection
@CollectionName(collectionName = "testData")
public class TestDao extends MongoDataBase implements IBaseDao{

	DBCollection dbc;

	TestDao(){super();}
	
	public TestDao(MongoDataSource mds) {
		super(mds);
		String s = ((org.mongo.annotation.CollectionName)AnnotationUtils.getAnnotationValueForClass(TestDao.class, CollectionName.class)).collectionName();
		dbc = super.getDBCollection(s);
	}
	
	public DBCollection getDatabaseCollection(){
		if(dbc == null){
			String s = ((org.mongo.annotation.CollectionName)AnnotationUtils.getAnnotationValueForClass(TestDao.class, CollectionName.class)).collectionName();
			dbc = super.getDBCollection(s);
		}
		return this.dbc;
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

	@Override
	public void updateDocumentWithDBObject(DBObject query, DBObject updateObject) {
		WriteResult wr = dbc.update(query, updateObject);
				if (wr.getError() != null && !wr.getError().equals("")) {
					throw new MongoException("Error while updating a document"
							+ wr.getError());
				}
	}

}
