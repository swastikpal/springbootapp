package com.gcdc.sample.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.gcdc.openapi.model.ModelApiResponse;
import com.gcdc.openapi.model.Pet;
import com.gcdc.sample.service.PetService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("${petstore.base-path:}")
public class PetController {

	@Autowired
	private PetService petService;

	/**
	 * 
	 * @param request
	 */
	public PetController(NativeWebRequest request) {
	}

	/***
	 * 
	 * @param body
	 * @return
	 */
	@PostMapping(value = "/pet/add", consumes = { "application/json", "application/xml" })
	public ResponseEntity<Void> addPet(@Valid @RequestBody(required = true) Pet payload) {
		try {
			petService.addPet(payload);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/***
	 * 
	 * @param status
	 * @return
	 */
	@GetMapping(value = "/pet/status", produces = { "application/xml", "application/json" })
	public ResponseEntity<List<Pet>> findPetsByStatus(
			@NotNull @Valid @RequestParam(value = "status", required = true) List<String> status) {
		List<Pet> pets = petService.getPetByStatus(status.get(0));
		return new ResponseEntity<>(pets, HttpStatus.OK);
	}

	/***
	 * get pet by id
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

	/**
	 * delete pet
	 * 
	 * @param petId
	 * @return
	 */
	@DeleteMapping(value = "/pet/delete/{petId}")
	public ResponseEntity<Void> deletePet(@PathVariable("petId") Long petId,
			@RequestHeader(value = "api_key", required = false) String apiKey) {
		try {
			petService.deletePet(petId);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * update pet
	 */
	@PutMapping(value = "/pet/update", consumes = { "application/json", "application/xml" })
	public ResponseEntity<Void> updatePet(@RequestBody(required = true) Pet pet) {
		return null;

	}

	@PostMapping(value = "/pet/form/{petId}", consumes = { "application/x-www-form-urlencoded" })
	public ResponseEntity<Void> updatePetWithForm(
			@ApiParam(value = "ID of pet that needs to be updated", required = true) @PathVariable("petId") Long petId,
			@ApiParam(value = "Updated name of the pet") @Valid @RequestPart(value = "name", required = false) String name,
			@ApiParam(value = "Updated status of the pet") @Valid @RequestPart(value = "status", required = false) String status) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

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
