package com.strr.licenses.controller;

import com.strr.licenses.model.License;
import com.strr.licenses.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/organizations/{organizationId}/licenses")
public class LicenseController {
    private final LicenseService licenseService;

    @Autowired
    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping
    public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
        return licenseService.getLicensesByOrg(organizationId);
    }

    @GetMapping("/{licenseId}")
    public License getLicensesWithClient(@PathVariable("organizationId") String organizationId,
                                         @PathVariable("licenseId") String licenseId) {
        return licenseService.getLicense(organizationId, licenseId);
    }

    @PutMapping
    public void updateLicenses(@RequestBody License license) {
        licenseService.updateLicense(license);
    }

    @PostMapping
    public void saveLicenses(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @DeleteMapping("{licenseId}")
    public void deleteLicenses(@PathVariable("licenseId") String licenseId) {
        licenseService.deleteLicense(licenseId);
    }
}
