package com.neaea_exam_admin.DAO;

import com.neaea_exam_admin.utilities.ConnManager;
import com.neaea_exam_admin.entity.WoredaAllowance;

public class WoredaAllowanceDAO {
	private ConnManager connManager;

	public WoredaAllowanceDAO(ConnManager _connManager) {
		connManager = _connManager;
	}

	public void persist(WoredaAllowance woredaAllowance) {
		String query = "INSERT INTO woredaallowance VALUES('"
				+ woredaAllowance.getWoredaAllowanceId() + "',"
				+ woredaAllowance.getLowScale() + ","
				+ woredaAllowance.getMiddiumScal() + ","
				+ woredaAllowance.getHighScale() + ","
				+ woredaAllowance.getDesertAllowance() + ","
				+ woredaAllowance.getWoreda().getWoredaId() + ")";
		connManager.executeCUD(query);
	}
}
