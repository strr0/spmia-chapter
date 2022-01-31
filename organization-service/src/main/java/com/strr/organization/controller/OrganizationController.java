package com.strr.organization.controller;

import com.strr.organization.model.Organization;
import com.strr.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="v1/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping(value="/{orgId}")
    public Organization getOrganization(@PathVariable("orgId") String orgId) {
        return organizationService.getOrg(orgId);
    }

    @PutMapping
    public void updateOrganization(@RequestBody Organization org) {
        organizationService.updateOrg(org);
    }

    @PostMapping
    public void saveOrganization(@RequestBody Organization org) {
        organizationService.saveOrg(org);
    }

    @DeleteMapping("/{orgId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("orgId") String orgId) {
        organizationService.deleteOrg(orgId);
    }
}
