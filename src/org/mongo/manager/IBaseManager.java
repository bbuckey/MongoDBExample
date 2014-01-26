package org.mongo.manager;

import java.util.List;

import org.mongo.entity.IBaseEntity;


public interface IBaseManager {
	public List getCollectionDocumentsAsList();
	public void addDocumentToCollection(IBaseEntity tde)throws Exception;
	public IBaseEntity findOneDocumentByKeyValue(String key, Object value);

}
