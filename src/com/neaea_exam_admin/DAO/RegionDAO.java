package com.neaea_exam_admin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neaea_exam_admin.entity.Region;
import com.neaea_exam_admin.utilities.ConnManager;

public class RegionDAO {

	private ConnManager connManager;

	public RegionDAO(ConnManager _connManager) {
		connManager = _connManager;
	}

	public List<Region> getById(int id) {
		String query = "SELECT * FROM region WHERE regioncode=" + id;
		return get(query);
	}
     public List<Region> getAll(){
    	 String query = "SELECT * FROM region ";
 		return get(query);
     }
	private List<Region> get(String query) {
		List<Region> regions = new ArrayList<Region>();
		ResultSet rs = connManager.executeRead(query);
		try {
			while (rs.next()) {
				Region region = new Region(rs.getString("regionName"),
						rs.getInt("regioncode"));
				regions.add(region);
			}

			return regions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
