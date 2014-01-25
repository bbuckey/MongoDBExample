package org.mongo.manager;

import java.util.List;
import org.mongo.manager.IBaseManager;
import org.mongo.entity.TestDataEnitity;

public interface ITestDataManager extends IBaseManager{
	public List getCollectionDocumentsAsList();
	public void addDocumentToCollection(TestDataEnitity tde)throws Exception;
	public TestDataEnitity findOneDocumentByKeyValue(String key, Object value);
}
