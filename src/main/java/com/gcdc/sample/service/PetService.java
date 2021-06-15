package com.gcdc.sample.service;

import java.util.List;

import com.gcdc.openapi.model.Pet;
import com.gcdc.sample.exception.PetException;

public interface PetService {
	
	public Pet getPet(Long id) throws PetException;
	
	public List<Pet> getPetByStatus(String status) throws PetException;
	
	public void addPet(Pet pet) throws PetException;
	
	public int getRowCount(String table);
	
	public void deletePet(Long petId) throws PetException;

}
