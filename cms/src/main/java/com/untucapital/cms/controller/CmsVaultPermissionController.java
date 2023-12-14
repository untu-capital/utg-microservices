package com.untucapital.cms.controller;

import com.untucapital.cms.dto.req.CmsVaultPermissionRequestDTO;
import com.untucapital.cms.dto.res.CmsVaultPermissionResponseDTO;
import com.untucapital.cms.service.CmsVaultPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cms/cms_vault_permission")
public class CmsVaultPermissionController {

    private static final Logger log = LoggerFactory.getLogger(CmsVaultPermissionController.class);
    @Autowired
    CmsVaultPermissionService cmsVaultPermissionService;

    @GetMapping
    public List<CmsVaultPermissionResponseDTO> list() {
        return cmsVaultPermissionService.getAllCmsVaultPermissions();
    }

    @PostMapping
    public void saveCmsVaultPermission(@RequestBody CmsVaultPermissionRequestDTO cmsVaultPermission) {
        log.info(String.valueOf(cmsVaultPermission));
        cmsVaultPermissionService.saveCmsVaultPermission(cmsVaultPermission);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        cmsVaultPermissionService.deleteCmsVaultPermission(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CmsVaultPermissionResponseDTO> getCmsVaultPermissionById(@PathVariable("id") String id) {
        CmsVaultPermissionResponseDTO cmsVaultPermission = cmsVaultPermissionService.getCmsVaultPermissionById(id);

        if (cmsVaultPermission !=null) {
            return new ResponseEntity<>(cmsVaultPermission, HttpStatus.OK);
        } else {
            // Handle the case when the CmsVaultPermissions object is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("user/{userId}")
    public List<CmsVaultPermissionResponseDTO> getCmsVaultPermissionByUserId(@PathVariable String userId) {
        return cmsVaultPermissionService.getCmsVaultPermissionByUserId(userId);
    }

//    @GetMapping("/getByVaultAccCode/{vaultAccCode}")
//    public ResponseEntity<List<CmsVaultPermission>> getCmsVaultPermissionByVaultAccCode(@PathVariable("vaultAccCode") String vaultAccCode) {
//        List<CmsVaultPermission> cmsVaultPermissions = cmsVaultPermissionService.getCmsVaultPermissionByVaultAccCode(vaultAccCode);
//
//        if (!cmsVaultPermissions.isEmpty()) {
//            return new ResponseEntity<>(cmsVaultPermissions, HttpStatus.OK);
//        } else {
//            // Handle the case when no CmsVaultPermission objects are found for the provided vault_acc_code
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }



}
