package com.untucapital.cms.service;

import com.untucapital.cms.dto.req.CmsVaultPermissionRequestDTO;
import com.untucapital.cms.dto.res.CmsVaultPermissionResponseDTO;
import com.untucapital.cms.entity.CmsVaultPermission;
import com.untucapital.cms.repository.CmsVaultPermissionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@jakarta.transaction.Transactional
public class CmsVaultPermissionService {

    @Autowired
    private CmsVaultPermissionRepository cmsVaultPermissionRepository;

    @Transactional(value = "transactionManager")
    public List<CmsVaultPermissionResponseDTO> getAllCmsVaultPermissions() {

        List<CmsVaultPermissionResponseDTO> response = new ArrayList<>();
        List<CmsVaultPermission> cmsVaultPermissionList = cmsVaultPermissionRepository.findAll();

        for(CmsVaultPermission cmsVaultPermission: cmsVaultPermissionList){

            CmsVaultPermissionResponseDTO responseDTO = new CmsVaultPermissionResponseDTO();
            BeanUtils.copyProperties(cmsVaultPermission, responseDTO);

            response.add(responseDTO);
        }
        return response;
    }

    @Transactional(value = "transactionManager")
    public void saveCmsVaultPermission(CmsVaultPermissionRequestDTO request) {

        CmsVaultPermission cmsVaultPermission = new CmsVaultPermission();
        BeanUtils.copyProperties(request, cmsVaultPermission);
        cmsVaultPermissionRepository.save(cmsVaultPermission);
    }

    @Transactional(value = "transactionManager")
    public CmsVaultPermissionResponseDTO getCmsVaultPermissionById(String id) {

        CmsVaultPermissionResponseDTO response = new CmsVaultPermissionResponseDTO();
        Optional<CmsVaultPermission> cmsVaultPermissionOptional =cmsVaultPermissionRepository.findById(id);

        if(cmsVaultPermissionOptional.isPresent()){

            CmsVaultPermission cmsVaultPermission = cmsVaultPermissionOptional.get();
            BeanUtils.copyProperties(cmsVaultPermission,response);

        } else {
            return null;
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public CmsVaultPermissionResponseDTO getCmsVaultPermissionByPoNumber(String id) {

        CmsVaultPermissionResponseDTO response = new CmsVaultPermissionResponseDTO();
        Optional<CmsVaultPermission> cmsVaultPermissionOptional= cmsVaultPermissionRepository.getCmsVaultPermissionById(id);

        if(cmsVaultPermissionOptional.isPresent()){

            CmsVaultPermission cmsVaultPermission = cmsVaultPermissionOptional.get();
            BeanUtils.copyProperties(cmsVaultPermission,response);

        } else {
            return null;
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<CmsVaultPermissionResponseDTO> getCmsVaultPermissionByUserId(String userId) {

        List<CmsVaultPermissionResponseDTO> response = new ArrayList<>();
        List<CmsVaultPermission> cmsVaultPermissionList= cmsVaultPermissionRepository.findCmsVaultPermissionsByUserid(userId);


        for(CmsVaultPermission cmsVaultPermission: cmsVaultPermissionList){

            CmsVaultPermissionResponseDTO responseDTO = new CmsVaultPermissionResponseDTO();
            BeanUtils.copyProperties(cmsVaultPermission, responseDTO);

            response.add(responseDTO);
        }
        return response;
    }


    @Transactional(value = "transactionManager")
    public void deleteCmsVaultPermission(String id) {
        cmsVaultPermissionRepository.deleteById(id);
    }

//    public List<CmsVaultPermission> getCmsVaultPermissionByVaultAccCode(String vaultAccCode) {
//        return cmsVaultPermissionRepository.getCmsVaultPermissionsByVault_acc_code(vaultAccCode);
//    }
}
