package com.gcdc.sample.dao.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PetMapper implements RowMapper<Pet> {

	@Override
	public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {

		Pet pet = new Pet();
		pet.setId(rs.getLong("id"));
		pet.setName(rs.getString("name"));
		pet.setStatus(rs.getString("status"));
		pet.setCategory(rs.getString("category"));
		pet.setTag(rs.getString("tag"));
		return pet;
	}

}
