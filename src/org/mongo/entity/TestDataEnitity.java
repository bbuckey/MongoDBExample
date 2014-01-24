package org.mongo.entity;

public class TestDataEnitity {

		String id;
		int x;
		String h;
		
		public TestDataEnitity(){;}
		
		public TestDataEnitity(String _id, int _x, String _h){
			id = _id;
			x = _x;
			h = _h;
		}
		
	public String getId(){
		return id;
	}
	
	public String getH(){
		return h;
	}
	
	public int getX(){
		return x;
	}

	
	public void setId(String _id){
		 id= _id;
	}
	
	public void setH( String _h){
		h = _h;
	}
	
	public void setX(int _x){
		 x = _x;
	}
	
	
}
