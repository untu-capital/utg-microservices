package com.untucapital.cms.repository;


import com.untucapital.cms.entity.CmsVaultPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CmsVaultPermissionRepository extends JpaRepository<CmsVaultPermission, String> {

    Optional<CmsVaultPermission> getCmsVaultPermissionById(String id);

    List<CmsVaultPermission> findCmsVaultPermissionsByUserid(String userId);

    List<CmsVaultPermission> findAllByUserid(String id);

//    List<CmsVaultPermission> getCmsVaultPermissionsByVault_acc_code (String vault_acc);

}
