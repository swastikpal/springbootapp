package com.gcdc.sample.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcdc.sample.dao.PetDataAccessLayer;
import com.gcdc.sample.dao.model.Pet;
import com.gcdc.sample.dao.model.PetMapper;

@Repository
public class PetDataAccessLayerImpl implements PetDataAccessLayer {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Pet getPetInformation(Long id) {
		Pet pet = jdbcTemplate.queryForObject("SELECT * FROM PET WHERE id = ?", new PetMapper(), new Object[] {id});
		return pet;
	}

	@Override
	public Pet save(Pet pet) {
		
		return pet;
	}

	@Override
	public int count(String table) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) from PET", Integer.class);
	}

	@Override
	public List<Pet> getPetbyStatus(String status) {
		List<Pet> petByStatus = jdbcTemplate.query("SELECT * FROM PET WHERE status = ?", new PetMapper(), new Object[] {status});
		return petByStatus;
	}

}
