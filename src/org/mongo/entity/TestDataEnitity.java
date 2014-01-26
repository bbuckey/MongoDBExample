package org.mongo.entity;

import org.bson.types.ObjectId;
import org.mongo.annotation.CollectionKey;

public class TestDataEnitity implements IBaseEntity{

	@CollectionKey(key = "_id")
	ObjectId id;
	@CollectionKey(key = "x")
	double x;
	@CollectionKey(key = "h")
	String h;
		
	public TestDataEnitity(){;}
		
	public TestDataEnitity(ObjectId _id, double _x, String _h){
			id = _id;
			x = _x;
			h = _h;
	}
		
	public ObjectId getId(){
		return id;
	}
	
	public String getH(){
		return h;
	}
	
	public double getX(){
		return x;
	}

	
	public void setId(ObjectId _id){
		 id= _id;
	}
	
	public void setH( String _h){
		h = _h;
	}
	
	public void setX(double _x){
		 x = _x;
	}
	
	
}
