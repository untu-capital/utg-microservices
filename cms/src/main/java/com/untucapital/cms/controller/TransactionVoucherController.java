package com.untucapital.cms.controller;

import com.untucapital.cms.dto.ApproverRequest;
import com.untucapital.cms.dto.TransactionVoucherInitiatorRequest;
import com.untucapital.cms.dto.TransactionVoucherResponse;
import com.untucapital.cms.dto.TransactionVoucherUpdateRequest;
import com.untucapital.cms.entity.Authorisation;
import com.untucapital.cms.entity.CmsVaultPermission;
import com.untucapital.cms.entity.User;
import com.untucapital.cms.service.TransactionVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tjchidanika
 * @created 4/10/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("cms/transaction-voucher")
public class TransactionVoucherController {

    private final TransactionVoucherService transactionVoucherService;

    //Initiate Transaction
    @PostMapping("/initiate")
    public ResponseEntity<TransactionVoucherResponse> initiateTransaction(@RequestBody TransactionVoucherInitiatorRequest request) {
        return ResponseEntity.ok(transactionVoucherService.initiateTransaction(request));
    }

    //First Approve Transaction
    @PostMapping("/first-approve")
    public ResponseEntity<TransactionVoucherResponse> firstApproveTransaction(@RequestBody ApproverRequest request) {
        return ResponseEntity.ok(transactionVoucherService.firstApproveTransaction(request));
    }

    //Second Approve Transaction
    @PostMapping("/second-approve")
    public ResponseEntity<TransactionVoucherResponse> secondApproveTransaction(@RequestBody ApproverRequest request) {
        return ResponseEntity.ok(transactionVoucherService.secondApproveTransaction(request));
    }

    //Get All Transactions
    @GetMapping("/all")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactions() {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactions());
    }

    //Get Transaction By id
    @GetMapping("/{id}")
    public ResponseEntity<TransactionVoucherResponse> getTransactionById(@PathVariable Integer id) {
        return ResponseEntity.ok(transactionVoucherService.getTransactionById(id));
    }

    //Get All Transactions By Branch
    @GetMapping("/all-by-branch/{branchId}")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactionsByBranch(@PathVariable String branchId) {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactionsByBranch(branchId));
    }

    //Get All Transactions By Vault
    @GetMapping("/all-by-vault/{vaultId}")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactionsByVault(@PathVariable Integer vaultId) {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactionsByVault(vaultId));
    }

    //Get All Transactions By Initiator
    @GetMapping("/all-by-initiator/{initiator}")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactionsByInitiator(@PathVariable String initiator) {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactionsByInitiator(initiator));
    }

    //Get All Transactions By First Approver
    @GetMapping("/all-by-first-approver/{firstApprover}")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactionsByFirstApprover(@PathVariable String firstApprover) {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactionsByFirstApprover(firstApprover));
    }

    //Get All Transactions By Second Approver
    @GetMapping("/all-by-second-approver/{secondApprover}")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactionsBySecondApprover(@PathVariable String secondApprover) {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactionsBySecondApprover(secondApprover));
    }

    //Initiator Update the Transaction If it is not yet approved or got rejected by the first approver
    @PutMapping("/update")
    public ResponseEntity<TransactionVoucherResponse> updateTransaction(@RequestBody TransactionVoucherUpdateRequest request) {
        return ResponseEntity.ok(transactionVoucherService.updateTransactionVoucher(request));
    }

    //Delete Transaction
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Integer id) {
        return ResponseEntity.ok(transactionVoucherService.deleteTransaction(id));
    }

    //Get CMS permissions
    @GetMapping("/permissions/{userId}")
    public ResponseEntity<List<CmsVaultPermission>> getCmsPermissions(@PathVariable String userId) {
        return ResponseEntity.ok(transactionVoucherService.getCmsVaultPermissionByUserId(userId));
    }

    //get authorization by user id
    @GetMapping("/authorizations/{userId}")
    public ResponseEntity<List<Authorisation>> getAuthorizations(@PathVariable String userId) {
        return ResponseEntity.ok(transactionVoucherService.getAuthorisationByUserId(userId));
    }

    //get first approver by branchId
    @GetMapping("/first-approvers/{branchId}")
    public ResponseEntity<List<User>> findFirstApprover(@PathVariable String branchId) {
        return ResponseEntity.ok(transactionVoucherService.findAllByBranchIdAndAuthLevel(branchId, "First Approver"));
    }

    //get second approver by branchId
    @GetMapping("/second-approvers/{branchId}")
    public ResponseEntity<List<User>> secondFirstApprover(@PathVariable String branchId) {
        return ResponseEntity.ok(transactionVoucherService.findAllByBranchIdAndAuthLevel(branchId, "Second Approver"));
    }

}
