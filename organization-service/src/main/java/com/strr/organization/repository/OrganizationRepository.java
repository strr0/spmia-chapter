package com.strr.organization.repository;

import com.strr.organization.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization,String> {
    //Organization findById(String organizationId);
}
