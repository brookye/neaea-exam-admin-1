package com.neaea_exam_admin.entity;

public class Woreda {
	private Zone zone;
	private String woredaName;
	private int woredaCode;
	private int woredaId;

	public Woreda(Zone _zone, String _woredaName, int _woredaCode, int _woredaId) {
		zone=_zone;
		woredaName=_woredaName;
		woredaCode=_woredaCode;
		woredaId=_woredaId;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public String getWoredaName() {
		return woredaName;
	}

	public void setWoredaName(String woredaName) {
		this.woredaName = woredaName;
	}

	public int getWoredaCode() {
		return woredaCode;
	}

	public void setWoredaCode(int woredaCode) {
		this.woredaCode = woredaCode;
	}

	public int getWoredaId() {
		return woredaId;
	}

	public void setWoredaId(int woredaId) {
		this.woredaId = woredaId;
	}
}
