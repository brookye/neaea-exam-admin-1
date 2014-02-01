package com.neaea_exam_admin.entity;

public class Zone {
	private Region region;
	private String zoneName;
	private int zoneCode;
	private int zoneId;

	public Zone(Region _regionCode, String _zoneName, int _zoneCode, int _zoneId) {
		region = _regionCode;
		zoneName = _zoneName;
		zoneCode = _zoneCode;
		zoneId = _zoneId;
	}

	public Region getRegionCode() {
		return region;
	}

	public void setRegionCode(Region regionCode) {
		this.region = regionCode;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public int getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(int zoneCode) {
		this.zoneCode = zoneCode;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

}
