package com.netcracker.DTO.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class IntegerMapper implements RowMapper<Integer> {

	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException{
		Integer res = rs.getInt("Maximum");
		
		return res;
	}
}
