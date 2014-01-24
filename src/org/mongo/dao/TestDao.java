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
import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.mongodb.MongoException;

public class TestDao extends MongoDataBase {

	DBCollection dbc;

	public TestDao(Properties props) throws UnknownHostException {
		super(props);
		dbc = super.getDBCollection(CollectionNames.TESTDATA);
	}

	public List getTestDataCollecton() {
		List l = new ArrayList();
		DBCursor cursor = dbc.find();
		/*
		 * DBObject dbo; while (cursor.hasNext()){ dbo = cursor.next();
		 * l.add(dbo); }
		 */
		return cursor.toArray();
	}

	public void addDBObjectToCollection(DBObject dbo) throws Exception {
		WriteResult wr = this.dbc.save(dbo);
		// this.dbc.insert(dbo);
		if (wr.getError() != null && !wr.getError().equals("")) {
			throw new MongoException("Error while inserting a document"
					+ wr.getError());
		}
		this.dbc.save(dbo);

	}

	public void removeTestDataDocumentFromCollection(String key, Object value)
			throws Exception {
		DBObject dbo = this.dbc.findOne((new BasicDBObject()).put(key, value));
		if (dbo != null && dbo.containsField(key) && dbo.get(key).equals(value)) {
			removeDBObjectFromCollection(dbo);
		} else {
			throw new MongoException("Error while removing a document: The document was not found");
		}

	}

	public void removeDBObjectFromCollection(DBObject dbo) throws Exception {
		WriteResult wr = this.dbc.remove(dbo);
		if (wr.getError() != null && !wr.getError().equals("")) {
			throw new MongoException("Error while removing a document"
					+ wr.getError());
		}
	}

}
