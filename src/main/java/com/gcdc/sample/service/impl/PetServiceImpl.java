package com.gcdc.sample.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gcdc.openapi.model.Category;
import com.gcdc.openapi.model.Pet;
import com.gcdc.openapi.model.Pet.StatusEnum;
import com.gcdc.openapi.model.Tag;
import com.gcdc.sample.dao.PetDataAccessLayer;
import com.gcdc.sample.dao.model.Fare;
import com.gcdc.sample.dao.model.TaxiRide;
import com.gcdc.sample.service.PetService;

@Component
public class PetServiceImpl implements PetService {

	@Autowired
	private PetDataAccessLayer petDao;

	@Autowired
	private KieContainer kieContainer;
	
	@Override
	public Pet getPet(Long id) {
		com.gcdc.sample.dao.model.Pet petRow = petDao.getPetInformation(id);
		Pet responsePet = null;
		if (petRow != null) {
			responsePet = convertToPetVO(petRow);
		}
		return responsePet;
	}

	@Override
	public List<Pet> getPetByStatus(String status) {
		List<Pet> pets = new ArrayList<Pet>();
		pets = convertToPetVOList(petDao.getPetbyStatus(status));

		return pets;
	}

	@Override
	public void addPet(Pet pet) throws Exception {
		petDao.save(convertToPetDTO(pet));
	}

	@Override
	public int getRowCount(String table) {
		return petDao.count(table);
	}

	@Override
	public void deletePet(Long petId) {
		try {
			petDao.deletePet(petId);
		} catch (Exception e) {
			throw e;
		}
		
	}

	private com.gcdc.sample.dao.model.Pet convertToPetDTO(Pet pet) {
		com.gcdc.sample.dao.model.Pet petDto = new com.gcdc.sample.dao.model.Pet();
		petDto.setId(pet.getId());
		petDto.setCategory(pet.getCategory().getName());
		petDto.setName(pet.getName());
		petDto.setStatus(pet.getStatus().toString());
		petDto.setTag(pet.getTags().get(0).getName());
		return petDto;

	}

	private Pet convertToPetVO(com.gcdc.sample.dao.model.Pet petRow) {
		Pet responsePet = new Pet();
		responsePet.setId(petRow.getId());
		responsePet.setName(petRow.getName());

		if (StatusEnum.AVAILABLE.toString().equalsIgnoreCase(petRow.getStatus())) {
			responsePet.setStatus(StatusEnum.AVAILABLE);
		} else if (StatusEnum.PENDING.toString().equalsIgnoreCase(petRow.getStatus())) {
			responsePet.setStatus(StatusEnum.PENDING);
		} else if (StatusEnum.SOLD.toString().equalsIgnoreCase(petRow.getStatus())) {
			responsePet.setStatus(StatusEnum.SOLD);
		}

		Category cat = new Category();
		cat.setId(1L);
		cat.setName(petRow.getCategory());
		responsePet.setCategory(cat);

		List<Tag> tags = new ArrayList<Tag>();
		tags.add(new Tag().id(1L).name(petRow.getTag()));
		responsePet.setTags(tags);
		return responsePet;
	}

	private List<Pet> convertToPetVOList(List<com.gcdc.sample.dao.model.Pet> petbyStatus) {
		List<Pet> pets = new ArrayList<Pet>();

		petbyStatus.forEach(petRow -> {
			Pet responsePet = convertToPetVO(petRow);
			pets.add(responsePet);

		});
		return pets;
	}

	@Override
	public Long calculateFare(TaxiRide taxiRide, Fare rideFare) {
		KieSession session = kieContainer.newKieSession();
		session.setGlobal("rideFare", rideFare);
		session.insert(taxiRide);
		session.fireAllRules();
		session.dispose();
        return rideFare.getRideFare();
	}

}
