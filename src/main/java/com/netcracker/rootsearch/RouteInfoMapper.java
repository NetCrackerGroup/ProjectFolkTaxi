package com.netcracker.rootsearch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RouteInfoMapper implements RowMapper<InfoAboutRoute> {

	public InfoAboutRoute mapRow(ResultSet rs, int rowNum) throws SQLException{
		InfoAboutRoute info = new InfoAboutRoute();
		
		info.setRouteId(rs.getLong("route_id"));
		info.setDistance((int)rs.getDouble("Distance"));
		info.setTimeUntilStart(rs.getString("timeUntilStart"));
		return info;
	}
	
}
