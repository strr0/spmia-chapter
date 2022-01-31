package com.strr.organization.service;

import com.strr.organization.model.Organization;
import com.strr.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization getOrg(String id) {
        return organizationRepository.findById(id).orElse(null);
    }

    public void saveOrg(Organization org){
        org.setId( UUID.randomUUID().toString());
        organizationRepository.save(org);

    }

    public void updateOrg(Organization org){
        organizationRepository.save(org);
    }

    public void deleteOrg(String id){
        organizationRepository.deleteById(id);
    }
}
