package com.gcdc.sample.dao;

import java.util.List;

import com.gcdc.sample.dao.model.Pet;

public interface PetDataAccessLayer {
	
	public Pet getPetInformation(Long id);
	
	public Pet save(Pet pet) throws Exception;
	
	public int count(String table);
	
	public List<Pet> getPetbyStatus(String status);
	
	public void deletePet(Long petId);
}
