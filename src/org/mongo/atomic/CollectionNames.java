package org.mongo.atomic;

public enum CollectionNames {
	//TESTDATA();
	TESTDATA("testData");
	CollectionNames(){;}
	String camelCase;
	CollectionNames(String _camelCase){camelCase = _camelCase;}
	public String getCamelCase(){return camelCase;}
}
