package com.neaea_exam_admin.entity;

public class Region {
   private String regionName;
   private int regionCode;

	public Region(String _regionName,int _regionCode){
	   regionName=_regionName;
	   regionCode=_regionCode;
   }

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(int regionCode) {
		this.regionCode = regionCode;
	}
}
