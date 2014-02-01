package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.entity.Woreda;
import com.neaea_exam_admin.entity.Zone;
import com.neaea_exam_admin.utilities.ConnManager;

public class WoredaDAO {
	private ConnManager connManager;

	public WoredaDAO(ConnManager _connConnManager) {
		connManager = _connConnManager;
	}

	public void persist(Woreda woreda) {
		String query = "INSERT INTO woreda VALUES(NULL,"
				+ woreda.getWoredaCode() + ",'" + woreda.getWoredaName() + "',"
				+ woreda.getZone().getZoneId() + ")";
		System.out.println("INFO:"+query);
		connManager.executeCUD(query);
	}

	public List<Woreda> getByZoneId(int zoneId) {
		String query = "SELECT * FROM woreda WHERE zoneId=" + zoneId;
		return get(query);
	}
    public List<Woreda> getById(int woredaId){
    	String query = "SELECT * FROM woreda WHERE woredId=" + woredaId;
		return get(query);
    }
	private List<Woreda> get(String query) {
		List<Woreda> woredas = new ArrayList<Woreda>();
		ResultSet rs = connManager.executeRead(query);
		ZoneDAO zoneDAO = new ZoneDAO(connManager);
		try {
			while (rs.next()) {
				Zone zone = zoneDAO.getZoneById(rs.getInt("zoneId")).get(0);
				Woreda woreda = new Woreda(zone, rs.getString("woredaname"),
						rs.getInt("woredacode"),rs.getInt("woredId"));
				woredas.add(woreda);
			}

			return woredas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
