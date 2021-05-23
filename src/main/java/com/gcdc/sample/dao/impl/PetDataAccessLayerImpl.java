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
		Pet pet = null;
		try {
			pet = jdbcTemplate.queryForObject("SELECT * FROM PET WHERE id = ?", new PetMapper(), new Object[] {id});
		} catch (Exception e) {
			pet = null;
		}
		return pet;
	}

	@Override
	public Pet save(Pet pet) throws Exception {
		try {
			jdbcTemplate.update("INSERT INTO PET (id, name, status, category, tag) VALUES (?, ?, ?, ?, ?)", new Object[] {pet.getId(), pet.getName(), pet.getStatus(), pet.getCategory(), pet.getTag()});
		} catch (Exception e) {
			throw e;
		}
		return pet;
	}

	@Override
	public int count(String table) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) from " + table, Integer.class);
	}

	@Override
	public List<Pet> getPetbyStatus(String status) {
		List<Pet> petByStatus = jdbcTemplate.query("SELECT * FROM PET WHERE status = ?", new PetMapper(), new Object[] {status});
		return petByStatus;
	}
	
	@Override
	public void deletePet(Long petId) {
		try {
			jdbcTemplate.update("DELETE FROM PET WHERE id = ?", new Object[] {petId});
		} catch (Exception e) {
			throw e;
		}
	}

}
