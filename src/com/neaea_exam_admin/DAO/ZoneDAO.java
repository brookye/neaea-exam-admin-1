package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.entity.Region;
import com.neaea_exam_admin.entity.Zone;
import com.neaea_exam_admin.utilities.ConnManager;

public class ZoneDAO {
	private ConnManager connManager;

	public ZoneDAO(ConnManager _connConnManager) {
		connManager = _connConnManager;
	}

	public void persist(Zone zone) {
		String persistQuery = "INSERT INTO zone VALUES(NULL," + zone.getZoneCode()
				+ ",'" + zone.getZoneName() + "'," + zone.getRegionCode().getRegionCode() + ")";
		connManager.executeCUD(persistQuery);
	}
    public List<Zone> getByRegionId(int regionId){
    	String query="SELECT * FROM zone WHERE regioncode="+regionId;
    	return get(query);
    }
	public void delete(Zone zone) {

	}

	public void update(Zone zone) {

	}
	public List<Zone> getZoneById(int id){
		String query="SELECT * FROM zone WHERE zoneId="+id;
		return get(query);
		
	}
	private List<Zone> get(String query) {
		List<Zone> zones= new ArrayList<Zone>();
		ResultSet rs = connManager.executeRead(query);
		RegionDAO regionDAO=new RegionDAO(connManager);
		try {
			while (rs.next()) {
				Region region=regionDAO.getById(rs.getInt("regioncode")).get(0);
				Zone zone = new Zone(region,rs.getString("zonename"),rs.getInt("zonecode"),0);
				zones.add(zone);
			}

			return zones;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Zone> getAll() {
		String query="SELECT * FROM zone";
		return get(query);
	}

}