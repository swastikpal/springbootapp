package com.gcdc.sample.ldap.repository;

import java.util.List;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.gcdc.sample.ldap.model.PetStoreAdmin;

@Repository
public interface PetStoreAdminRepository extends LdapRepository<PetStoreAdmin> {

	PetStoreAdmin findByUsername(String username);

	PetStoreAdmin findByUsernameAndPassword(String username, String password);

	List<PetStoreAdmin> findByUsernameLikeIgnoreCase(String username);

}
