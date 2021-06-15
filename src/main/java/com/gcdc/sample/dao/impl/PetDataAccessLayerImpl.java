package com.gcdc.sample.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcdc.sample.dao.PetDataAccessLayer;
import com.gcdc.sample.dao.model.Pet;
import com.gcdc.sample.dao.model.PetMapper;
import com.gcdc.sample.exception.PetException;

@Repository
public class PetDataAccessLayerImpl implements PetDataAccessLayer {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Pet getPetInformation(Long id) throws PetException {
		Pet pet = null;
		try {
			pet = jdbcTemplate.queryForObject("SELECT * FROM PET WHERE id = ?", new PetMapper(), new Object[] {id});
		} catch (Exception e) {
			throw new PetException("Not found", e);
		}
		return pet;
	}

	@Override
	public Pet save(Pet pet) throws PetException {
		try {
			jdbcTemplate.update("INSERT INTO PET (id, name, status, category, tag) VALUES (?, ?, ?, ?, ?)", new Object[] {pet.getId(), pet.getName(), pet.getStatus(), pet.getCategory(), pet.getTag()});
		} catch (Exception e) {
			throw new PetException(e);
		}
		return pet;
	}

	@Override
	public int count(String table) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) from " + table, Integer.class);
	}

	@Override
	public List<Pet> getPetbyStatus(String status) throws PetException {
		List<Pet> petByStatus = null;
		try {
			petByStatus = jdbcTemplate.query("SELECT * FROM PET WHERE status = ?", new PetMapper(), new Object[] {status});
		} catch (DataAccessException de) {
			throw new PetException(de);
		}
		return petByStatus;
	}
	
	@Override
	public void deletePet(Long petId) throws PetException {
		try {
			jdbcTemplate.update("DELETE FROM PET WHERE id = ?", new Object[] {petId});
		} catch (Exception e) {
			throw new PetException("Delete unsuccessfull", e);
		}
	}

}
