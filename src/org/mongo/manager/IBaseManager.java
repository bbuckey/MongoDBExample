package org.mongo.manager;

import java.util.List;
import java.util.Map;

import org.mongo.entity.BaseEntity;
import org.mongo.entity.IBaseEntity;


public interface IBaseManager<T> {
	public List getCollectionDocumentsAsList();
	public void addDocumentToCollection(BaseEntity tde)throws Exception;
	public void addDocumentToCollection(Map m)throws Exception;
	public IBaseEntity<? extends BaseEntity> findOneDocumentByKeyValue(String key, Object value);

}
