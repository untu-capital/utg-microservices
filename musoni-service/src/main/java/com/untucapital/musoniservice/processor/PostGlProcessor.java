package com.untucapital.musoniservice.processor;

import com.untucapital.musoniservice.client.RestClient;
import com.untucapital.musoniservice.dto.VaultRequest;
import com.untucapital.musoniservice.dto.req.PostGLRequestDTO;
import com.untucapital.musoniservice.dto.res.VaultResponseDTO;
import com.untucapital.musoniservice.entity.res.AccountEntityResponseDTO;
import com.untucapital.musoniservice.entity.res.PostGlResponseDTO;
import com.untucapital.musoniservice.model.User;
import com.untucapital.musoniservice.model.Vault;
import com.untucapital.musoniservice.model.transactions.TransactionInfo;
import com.untucapital.musoniservice.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
@AllArgsConstructor
public class PostGlProcessor {


    private final AccountService accountService;
    private final RestClient restClient;


    public PostGlResponseDTO createFromAccountRequest(TransactionInfo transactionInfo) {

        PostGlResponseDTO postGl = new PostGlResponseDTO();
        PostGLRequestDTO request = new PostGLRequestDTO();
        request.setTxDate(Date.valueOf(transactionInfo.getTransactionDate()));
        request.setReference(transactionInfo.getReference());
        request.setDescription(transactionInfo.getDescription());

        // Get the current date and time
        java.util.Date utilDate = new java.util.Date();

        // Convert the java.util.Date to java.sql.Date
        Date sqlDate = new Date(utilDate.getTime());
        request.setDTStamp(sqlDate);
        request.setICurrencyID(1);
        request.setId("JL");
        request.setICurrencyID(1);
        request.setFExchangeRate(1.0f);
        request.setBIsJCDocLine(false);
        request.setBIsSTGLDocLine(false);
        request.setBPrintCheque(false);
        request.setIInvLineID(0L);
        request.setBPBTPaid(false);
        request.setBReconciled(false);
        request.setUserName(transactionInfo.getUserName());
        request.setFExchangeRate(0F);
        request.setFForeignDebit(0F);
        request.setFForeignCredit(0F);
        request.setTaxTypeID(0);
        request.setTax_Amount(0F);
        request.setProject(0);
        request.setDrCrAccount(0);
        request.setJobCodeLink(0);
        request.setRepID(0);
        request.setFJCRepCost(0F);
        request.setIMFPID(0);
        request.setITxBranchID(0);
        request.setIGLTaxAccountID(0);
        request.setPostGL_iBranchID(0);
        request.setIImportDeclarationID(0);
        request.setIMajorIndustryCodeID(0);
        request.setFForeignTax(0F);


        AccountEntityResponseDTO accountEntity = accountService.findAccountByAccount(transactionInfo.getFromAccount());


        request.setCredit(transactionInfo.getAmount());
        request.setDebit(0f);
        request.setAccountLink(accountEntity.getAccountLink());

        BeanUtils.copyProperties(request, postGl);
        return postGl;
    }

    public PostGlResponseDTO createToAccountRequest(TransactionInfo transactionInfo) {

        PostGlResponseDTO postGl = new PostGlResponseDTO();
        PostGLRequestDTO request = new PostGLRequestDTO();
        request.setTxDate(Date.valueOf(transactionInfo.getTransactionDate()));
        request.setReference(transactionInfo.getReference());
        request.setDescription(transactionInfo.getDescription());

        // Get the current date and time
        java.util.Date utilDate = new java.util.Date();

        // Convert the java.util.Date to java.sql.Date
        Date sqlDate = new Date(utilDate.getTime());
        request.setDTStamp(sqlDate);
        request.setICurrencyID(1);
        request.setId("JL");
        request.setICurrencyID(1);
        request.setFExchangeRate(1.0f);
        request.setBIsJCDocLine(false);
        request.setBIsSTGLDocLine(false);
        request.setBPrintCheque(false);
        request.setIInvLineID(0L);
        request.setBPBTPaid(false);
        request.setBReconciled(false);
        request.setUserName(transactionInfo.getUserName());
        request.setFExchangeRate(0F);
        request.setFForeignDebit(0F);
        request.setFForeignCredit(0F);
        request.setTaxTypeID(0);
        request.setTax_Amount(0F);
        request.setProject(0);
        request.setDrCrAccount(0);
        request.setJobCodeLink(0);
        request.setRepID(0);
        request.setFJCRepCost(0F);
        request.setIMFPID(0);
        request.setITxBranchID(0);
        request.setIGLTaxAccountID(0);
        request.setPostGL_iBranchID(0);
        request.setIImportDeclarationID(0);
        request.setIMajorIndustryCodeID(0);
        request.setFForeignTax(0F);


        AccountEntityResponseDTO accountEntity = accountService.findAccountByAccount(transactionInfo.getToAccount());

        request.setCredit(0f);
        request.setDebit(transactionInfo.getAmount());
        request.setAccountLink(accountEntity.getAccountLink());

        BeanUtils.copyProperties(request, postGl);

        return postGl;
    }

    public void checkLimits(TransactionInfo request, Float currentBalanceFromAccount, Float currentBalanceToAccount, List<User> user ){



        if (currentBalanceFromAccount != null) {
            VaultResponseDTO optionalVault = restClient.findVaultByAccount(request.getFromAccount());

            if(optionalVault != null) {
                BigDecimal vaultBalance = BigDecimal.valueOf(currentBalanceFromAccount).subtract(BigDecimal.valueOf(request.getAmount()));
                optionalVault.setCurrentAmount(vaultBalance);

                VaultRequest request1 = new VaultRequest();

                BeanUtils.copyProperties(optionalVault,request1);

                restClient.saveVault(request1);
            }
        }

        if(currentBalanceToAccount != null) {

            VaultResponseDTO optionalVault = restClient.findVaultByAccount(request.getFromAccount());

            if(optionalVault !=null) {


                BigDecimal vaultBalance = BigDecimal.valueOf(currentBalanceToAccount).add(BigDecimal.valueOf(request.getAmount()));

                int value = BigDecimal.valueOf(currentBalanceToAccount).compareTo(optionalVault.getMaxAmount());
                if(value<=0) {

                    int result = optionalVault.getMaxAmount().compareTo(vaultBalance);
                    if (result < 0) {
                        //TODO Send email

//                        sendEmail(
//                                "Panashe" + " " + "Rutimhu",
//                                "panasherutimhu0@gmail.com",
//                                "Limit Exceeded",
//                                "The balance of this Vault : (" + vault.getAccount() + ") has exceeded the limit.",
//                                "Panashe" + " " + "Rutimhu"
//                        );
                    } else {
                        optionalVault.setCurrentAmount(vaultBalance);
                        VaultRequest request1 = new VaultRequest();
                        BeanUtils.copyProperties(optionalVault,request1);
                        restClient.saveVault(request1);
                    }
                }else {
                    optionalVault.setCurrentAmount(vaultBalance);
                    VaultRequest request1 = new VaultRequest();
                    BeanUtils.copyProperties(optionalVault,request1);
                    restClient.saveVault(request1);
                }
            }
        }
    }


    //TODO: Send Email to the user
//    private void sendEmail(String recipientName, String recipientEmail, String recipientSubject, String recipientMessage, String senderName) {
//        String emailText = emailSender.limitExceeded(recipientName, recipientMessage, senderName);
//        emailSender.send(recipientEmail, recipientSubject, emailText);
//    }
}
