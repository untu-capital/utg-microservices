package com.untucapital.cms.controller;


import com.untucapital.cms.dto.ApproverRequest;
import com.untucapital.cms.dto.AuditTrailInitiatorRequest;
import com.untucapital.cms.dto.ChangeAmountRequest;
import com.untucapital.cms.dto.res.AuditTrailResponseDTO;
import com.untucapital.cms.service.AuditTrailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@RestController
@RequestMapping("/cms/audit-trails")
@RequiredArgsConstructor
public class AuditTrailController {

    private final AuditTrailService auditTrailService;

    @GetMapping("/all")
    public ResponseEntity<List<AuditTrailResponseDTO>> getAllAuditTrails() {
        return ResponseEntity.ok(auditTrailService.getAllAuditTrails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditTrailResponseDTO> getAuditTrailById(@PathVariable Integer id) {
        return ResponseEntity.ok(auditTrailService.getAuditTrailById(id));
    }

    //Add Initiator
    @PostMapping("/add-initiator")
    public ResponseEntity<AuditTrailResponseDTO> addInitiator(@RequestBody AuditTrailInitiatorRequest request) {
        return ResponseEntity.ok(auditTrailService.addInitiator(request));
    }

    //Add First Approver
    @PutMapping("/add-first-approver")
    public ResponseEntity<AuditTrailResponseDTO> addFirstApprover(@RequestBody ApproverRequest request) {
        return ResponseEntity.ok(auditTrailService.addFirstApprover(request));
    }

    //Add Second Approver
    @PutMapping("/add-second-approver")
    public ResponseEntity<AuditTrailResponseDTO> addSecondApprover(@RequestBody ApproverRequest request) {
        return ResponseEntity.ok(auditTrailService.addSecondApprover(request));
    }

    //Delete Audit Trail
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuditTrail(@PathVariable Integer id) {

        return ResponseEntity.ok(auditTrailService.deleteAuditTrail(id));
    }

    //Update Audit Trail
    @PutMapping("/update")
    public ResponseEntity<AuditTrailResponseDTO> updateAuditTrail(@RequestBody ChangeAmountRequest request) {
        return ResponseEntity.ok(auditTrailService.updateAmount(request));
    }

}
