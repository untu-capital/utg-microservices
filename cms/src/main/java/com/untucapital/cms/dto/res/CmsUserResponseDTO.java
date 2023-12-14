package com.untucapital.cms.dto.res;

import com.untucapital.cms.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */

@Data
@RequiredArgsConstructor
public class CmsUserResponseDTO extends AbstractEntityDTO {

    private String role;
    private String teller_vault;
    private String branch_vault;
    private String external_vault;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTeller_vault() {
        return teller_vault;
    }

    public void setTeller_vault(String teller_vault) {
        this.teller_vault = teller_vault;
    }

    public String getBranch_vault() {
        return branch_vault;
    }

    public void setBranch_vault(String branch_vault) {
        this.branch_vault = branch_vault;
    }

    public String getExternal_vault() {
        return external_vault;
    }

    public void setExternal_vault(String external_vault) {
        this.external_vault = external_vault;
    }
}
