package com.untucapital.cms.processor;


import com.untucapital.cms.client.RestClient;
import com.untucapital.cms.dto.*;
import com.untucapital.cms.dto.res.TransactionPurposeResponseDTO;
import com.untucapital.cms.dto.res.VaultResponseDTO;
import com.untucapital.cms.entity.*;
import com.untucapital.cms.enums.ApprovalStatus;
import com.untucapital.cms.service.TransactionPurposeService;
import com.untucapital.cms.service.VaultService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
@AllArgsConstructor
public class TransactionVoucherProcessor {

    private final VaultService vaultService;
    private final TransactionPurposeService transactionPurposeService;
    private final RestClient restClient;



    public TransactionVoucher processTransactionVoucher(TransactionVoucherInitiatorRequest request){

        Vault fromVault = new Vault();
        Vault toVault = new Vault();
        User user = restClient.findUser(request.getInitiator());

        User firstApprover = restClient.findUser(request.getFirstApprover());
        User secondApprover = restClient.findUser(request.getSecondApprover());

        VaultResponseDTO vaultFrom = vaultService.getVault(Integer.valueOf(request.getFromVault()));
        BeanUtils.copyProperties(vaultFrom, fromVault);
        VaultResponseDTO vaultTo = vaultService.getVault(Integer.valueOf(request.getToVault()));
        BeanUtils.copyProperties(vaultTo, toVault);

        TransactionPurpose transactionPurpose = new TransactionPurpose();
        TransactionPurposeResponseDTO transactionPurposeResponseDTO = transactionPurposeService.getById(Integer.valueOf(request.getWithdrawalPurpose()));
        BeanUtils.copyProperties(transactionPurposeResponseDTO, transactionPurpose);

        Branches branch = fromVault.getBranch();

        return TransactionVoucher.builder()
                .initiator(user)
                .applicationDate(LocalDateTime.now())
                .amount(request.getAmount())
                .fromVault(fromVault)
                .toVault(toVault)
                .amountInWords(request.getAmountInWords())
                .withdrawalPurpose(transactionPurpose)
                .currency(request.getCurrency())
                .denomination100(request.getDenomination100())
                .denomination50(request.getDenomination50())
                .denomination20(request.getDenomination20())
                .denomination10(request.getDenomination10())
                .denomination5(request.getDenomination5())
                .denomination2(request.getDenomination2())
                .denomination1(request.getDenomination1())
                .denominationCents(request.getDenominationCents())
                .branch(branch)
                .firstApprover(firstApprover)
                .firstApprovalStatus(ApprovalStatus.PENDING)
                .secondApprover(secondApprover)
                .secondApprovalStatus(ApprovalStatus.PENDING)
                .build();
    }

    public TransactionVoucher processUpdatedTransactionVoucher(TransactionVoucher transactionVoucher, TransactionVoucherUpdateRequest request){

        Vault fromVault = new Vault();
        Vault toVault = new Vault();

        Integer fromVaultOldId = transactionVoucher.getFromVault().getId();
        Integer toVaultOldId = transactionVoucher.getToVault().getId();
        Integer oldTransactionId = transactionVoucher.getWithdrawalPurpose().getId();

        if (request.getFromVault() != null) {
            fromVaultOldId = request.getFromVault();
        }

        if (request.getToVault() != null) {
            toVaultOldId = request.getToVault();
        }

        if (request.getWithdrawalPurpose() != null) {
            oldTransactionId = request.getWithdrawalPurpose();
        }

        VaultResponseDTO vaultFrom = vaultService.getVault(fromVaultOldId);
        BeanUtils.copyProperties(vaultFrom, fromVault);
        VaultResponseDTO vaultTo = vaultService.getVault(toVaultOldId);
        BeanUtils.copyProperties(vaultTo, toVault);

        TransactionPurpose transactionPurpose = new TransactionPurpose();
        TransactionPurposeResponseDTO transactionPurposeResponseDTO = transactionPurposeService.getById(oldTransactionId);
        BeanUtils.copyProperties(transactionPurposeResponseDTO, transactionPurpose);

        if (transactionVoucher.getFirstApprovalStatus() == ApprovalStatus.APPROVED && transactionVoucher.getSecondApprovalStatus() == ApprovalStatus.APPROVED) {
            throw new RuntimeException("Transaction already Approved.");
        }

        if (request.getAmount() != null && !request.getAmount().equals(transactionVoucher.getAmount())) {
            transactionVoucher.setAmount(request.getAmount());
        }

        if (request.getFromVault() != null && fromVault != transactionVoucher.getFromVault()) {
            transactionVoucher.setFromVault(fromVault);
        }

        if (request.getToVault() != null && toVault != transactionVoucher.getToVault()) {
            transactionVoucher.setToVault(toVault);
        }

        if (request.getAmountInWords() != null && !request.getAmountInWords().equalsIgnoreCase(transactionVoucher.getAmountInWords())) {
            transactionVoucher.setAmountInWords(request.getAmountInWords());
        }

        if (request.getWithdrawalPurpose() != null && transactionPurpose != transactionVoucher.getWithdrawalPurpose()) {
            transactionVoucher.setWithdrawalPurpose(transactionPurpose);
        }

        if (request.getCurrency() != null && !request.getCurrency().equalsIgnoreCase(transactionVoucher.getCurrency())) {
            transactionVoucher.setCurrency(request.getCurrency());
        }

        if (request.getDenomination100() != null && !request.getDenomination100().equals(transactionVoucher.getDenomination100())) {
            transactionVoucher.setDenomination100(request.getDenomination100());
        }

        if (request.getDenomination50() != null && !request.getDenomination50().equals(transactionVoucher.getDenomination50())) {
            transactionVoucher.setDenomination50(request.getDenomination50());
        }

        if (request.getDenomination20() != null && !request.getDenomination20().equals(transactionVoucher.getDenomination20())) {
            transactionVoucher.setDenomination20(request.getDenomination20());
        }

        if (request.getDenomination10() != null && !request.getDenomination10().equals(transactionVoucher.getDenomination10())) {
            transactionVoucher.setDenomination10(request.getDenomination10());
        }

        if (request.getDenomination5() != null && !request.getDenomination5().equals(transactionVoucher.getDenomination5())) {
            transactionVoucher.setDenomination5(request.getDenomination5());
        }

        if (request.getDenomination2() != null && !request.getDenomination2().equals(transactionVoucher.getDenomination2())) {
            transactionVoucher.setDenomination2(request.getDenomination2());
        }

        if (request.getDenomination1() != null && !request.getDenomination1().equals(transactionVoucher.getDenomination1())) {
            transactionVoucher.setDenomination1(request.getDenomination1());
        }

        if (request.getDenominationCents() != null && !request.getDenominationCents().equals(transactionVoucher.getDenominationCents())) {
            transactionVoucher.setDenomination1(request.getDenominationCents());
        }

        transactionVoucher.setFirstApprovalStatus(ApprovalStatus.PENDING);
        transactionVoucher.setSecondApprovalStatus(ApprovalStatus.PENDING);

        return transactionVoucher;
    }
    public TransactionVoucherResponse transactionVoucherResponseSerializer(TransactionVoucher transaction) {

        UserDTO initiator = UserDTO.builder()
                .id(transaction.getInitiator().getId())
                .firstName(transaction.getInitiator().getFirstName())
                .lastName(transaction.getInitiator().getLastName())
                .build();

        UserDTO firstApprover = UserDTO.builder()
                .id(transaction.getFirstApprover().getId())
                .firstName(transaction.getFirstApprover().getFirstName())
                .lastName(transaction.getFirstApprover().getLastName())
                .build();

        UserDTO secondApprover = UserDTO.builder()
                .id(transaction.getSecondApprover().getId())
                .firstName(transaction.getSecondApprover().getFirstName())
                .lastName(transaction.getSecondApprover().getLastName())
                .build();

        BranchDTO branch = BranchDTO.builder()
                .id(transaction.getBranch().getId())
                .name(transaction.getBranch().getBranchName())
                .build();

        VaultDTO fromVault = VaultDTO.builder()
                .id(transaction.getFromVault().getId())
                .account(transaction.getFromVault().getAccount())
                .type(transaction.getFromVault().getType())
                .name(transaction.getFromVault().getName())
                .build();

        VaultDTO toVault = VaultDTO.builder()
                .id(transaction.getToVault().getId())
                .account(transaction.getToVault().getAccount())
                .type(transaction.getToVault().getType())
                .name(transaction.getToVault().getName())
                .build();

        return TransactionVoucherResponse.builder()
                .id(transaction.getId())
                .applicationNo(createApplicationId(transaction.getApplicationDate(), transaction.getId()))
                .initiator(initiator)
                .applicationDate(dateFormatter(transaction.getApplicationDate()))
                .firstApprover(firstApprover)
                .firstApprovedAt(dateFormatter(transaction.getFirstApprovedAt()))
                .firstApprovalStatus(transaction.getFirstApprovalStatus())
                .firstApprovalComment(transaction.getFirstApprovalComment())
                .secondApprover(secondApprover)
                .secondApprovedAt(dateFormatter(transaction.getSecondApprovedAt()))
                .secondApprovalStatus(transaction.getSecondApprovalStatus())
                .secondApprovalComment(transaction.getSecondApprovalComment())
                .amountInWords(transaction.getAmountInWords())
                .currency(transaction.getCurrency())
                .amount(transaction.getAmount())
                .denomination1(transaction.getDenomination1())
                .denomination2(transaction.getDenomination2())
                .denomination5(transaction.getDenomination5())
                .denomination10(transaction.getDenomination10())
                .denomination20(transaction.getDenomination20())
                .denomination50(transaction.getDenomination50())
                .denomination100(transaction.getDenomination100())
                .denominationCents(transaction.getDenominationCents())
                .withdrawalPurpose(transaction.getWithdrawalPurpose().getName())
                .branch(branch)
                .fromVault(fromVault)
                .toVault(toVault)
                .build();
    }

    private String dateFormatter(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd (HH:mm)");

        if (date == null) {
            return null;
        }

        return date.format(formatter);
    }

    public String createApplicationId(LocalDateTime date, Integer id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        return date.format(formatter)+'-'+id;
    }
}
