package com.gcdc.sample.dao;

import java.util.List;

import com.gcdc.sample.dao.model.Pet;
import com.gcdc.sample.exception.PetException;

public interface PetDataAccessLayer {
	
	public Pet getPetInformation(Long id) throws PetException;
	
	public Pet save(Pet pet) throws PetException;
	
	public int count(String table);
	
	public List<Pet> getPetbyStatus(String status) throws PetException;
	
	public void deletePet(Long petId) throws PetException;
}
