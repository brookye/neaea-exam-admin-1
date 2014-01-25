package com.neaea_exam_admin.entity;

/**
 * 
 * @author misgana
 * 
 */
// TODO use decimal types for decimal column types
public class WoredaAllowance {
	private int lowScale;
	private int meddiumScale;
	private int highScale;
	private Woreda woreda; // FK
	private int woredaAllowanceId; // PK

	public WoredaAllowance(int _lowScale, int _meddiumScale, int _highScale,
			Woreda _woreda, int _woredAllowanceId) {
     lowScale=_lowScale;
     meddiumScale=_meddiumScale;
     highScale=_highScale;
     woreda=_woreda;
     woredaAllowanceId=_woredAllowanceId;
     
	}

	public int getLowScale() {
		return lowScale;
	}

	public void setLowScale(int lowScale) {
		this.lowScale = lowScale;
	}

	public int getMiddiumScal() {
		return meddiumScale;
	}

	public void setMiddiumScal(int middiumScal) {
		this.meddiumScale = middiumScal;
	}

	public int getHighScale() {
		return highScale;
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

	public int getWoredaAllowanceId() {
		return woredaAllowanceId;
	}

	public void setWoredaAllowanceId(int woredaAllowanceId) {
		this.woredaAllowanceId = woredaAllowanceId;
	}

}
