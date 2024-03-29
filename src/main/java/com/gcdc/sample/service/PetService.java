package com.gcdc.sample.service;

import java.util.List;

import com.gcdc.openapi.model.Pet;

public interface PetService {
	
	public Pet getPet(Long id);
	
	public List<Pet> getPetByStatus(String status);
	
	public void addPet(Pet pet) throws Exception;
	
	public int getRowCount(String table);
	
	public void deletePet(Long petId);

}
