package com.neaea_exam_admin.entity;

/**
 * 
 * @author misgana
 * 
 */
// TODO use decimal types for decimal column types
public class WoredaAllowance {
	private float lowScale;
	private float meddiumScale;
	private float highScale;
	private float desertAllowance;
	private Woreda woreda; // FK

	private String woredaAllowanceId; // PK

	public WoredaAllowance(float _lowScale, float _meddiumScale,
			float _highScale, float _desertAllowance, Woreda _woreda,
			String _woredAllowanceId) {
		desertAllowance = _desertAllowance;
		lowScale = _lowScale;
		meddiumScale = _meddiumScale;
		highScale = _highScale;
		woreda = _woreda;
		woredaAllowanceId = _woredAllowanceId;

	}

	public float getDesertAllowance() {
		return desertAllowance;
	}

	public void setDesertAllowance(float desertAllowance) {
		this.desertAllowance = desertAllowance;
	}

	public float getLowScale() {
		return lowScale;
	}

	public void setLowScale(int lowScale) {
		this.lowScale = lowScale;
	}

	public float getMiddiumScal() {
		return meddiumScale;
	}

	public void setMiddiumScal(int middiumScal) {
		this.meddiumScale = middiumScal;
	}

	public float getHighScale() {
		return highScale;
	}

	public float getMeddiumScale() {
		return meddiumScale;
	}

	public void setMeddiumScale(float meddiumScale) {
		this.meddiumScale = meddiumScale;
	}

	public void setLowScale(float lowScale) {
		this.lowScale = lowScale;
	}

	public void setHighScale(float highScale) {
		this.highScale = highScale;
	}

	public void setHighScale(int highScale) {
		this.highScale = highScale;
	}

	public Woreda getWoreda() {
		return woreda;
	}

	public void setWoreda(Woreda woreda) {
		this.woreda = woreda;
	}

	public String getWoredaAllowanceId() {
		return woredaAllowanceId;
	}

	public void setWoredaAllowanceId(String woredaAllowanceId) {
		this.woredaAllowanceId = woredaAllowanceId;
	}

}
