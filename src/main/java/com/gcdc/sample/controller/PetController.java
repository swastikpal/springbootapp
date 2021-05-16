package com.gcdc.sample.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcdc.openapi.model.ModelApiResponse;
import com.gcdc.openapi.model.Pet;
import com.gcdc.sample.service.PetService;

@Controller
public class PetController {

	@Autowired
	private PetService petService;

	/***
	 * 
	 * @param body
	 * @return
	 */
	@PostMapping(value = "/pet/add/newpet", consumes = { "application/json", "application/xml" })
	public ResponseEntity<Pet> addNewPet(@Valid @RequestBody(required = true) Pet body) {
		petService.addPet(body);
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	/***
	 * 
	 * @param status
	 * @return
	 */
	@GetMapping(value = "/pet/findByCurrentStatus", produces = { "application/xml", "application/json" })
	public ResponseEntity<List<Pet>> findPetsByStatus(
			@NotNull @Valid @RequestParam(value = "status", required = true) String status) {
		List<Pet> pets = petService.getPetByStatus(status);
		return new ResponseEntity<>(pets, HttpStatus.OK);
	}

	/***
	 * 
	 * 
	 */
	@GetMapping(value = "/pet/id/{petId}", produces = { "application/xml", "application/json" })
	public ResponseEntity<Pet> getPetById(@PathVariable("petId") Long petId) {
		Pet pet = petService.getPet(petId);
		if (pet == null) {
			return new ResponseEntity<Pet>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pet, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/pet/delete/{petId}")
	public ResponseEntity<Integer> deletePet(@PathVariable("petId") Long petId) {
		petService.deletePet(petId);
		return new ResponseEntity<Integer>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/pet/update")
	public ResponseEntity<Integer> updatePet(@RequestBody(required = true) Pet pet) {
		return null;
		
	}

	/**
	 * Generic method to validate database connectivity
	 * 
	 * @param table
	 * @return
	 */
	@GetMapping(value = "/getRowCount", produces = { "application/xml", "application/json" })
	public ResponseEntity<ModelApiResponse> getRowCount(
			@NotNull @Valid @RequestParam(value = "table", required = true) String table) {
		Integer count = petService.getRowCount(table);

		ModelApiResponse response = new ModelApiResponse();
		response.setCode(count);
		response.setMessage("Database Connection established");
		response.setType("Connectivity Check");

		return new ResponseEntity<ModelApiResponse>(response, HttpStatus.OK);
	}

}
