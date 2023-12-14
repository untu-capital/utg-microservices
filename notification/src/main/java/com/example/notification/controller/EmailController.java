package com.example.notification.controller;


import com.example.notification.dto.messaging.*;
import com.example.notification.service.EmailSender;
import com.example.notification.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j

@RequestMapping(value = "email", produces = "application/json")
public class EmailController {

    private final EmailSender emailSender;

    @PostMapping("send")
    public void send(@RequestBody SendEmailRequest request) {

        String recipient = request.getRecipientEmail();
        String subject = request.getRecipientSubject();
        String msg = request.getEmailText();

        emailSender.send(recipient, subject, msg);
    }

    @PostMapping("sendBulk")
    public void sendBulk(@RequestBody BulkEmail request) {

        String[] recipient = request.getRecipients();
        String subject = request.getSubject();
        String msg = request.getMessage();

        emailSender.sendBulk(recipient, subject, msg);
    }

    @PostMapping("sendBulkEmail")
    public void sendBulkEmail(@RequestBody BulkEmail request) {

        String[] recipients = request.getRecipients();
        String subject = request.getSubject();
        String msg = request.getMessage();

        emailSender.sendBulkEmail(recipients, subject, msg);
    }

    @PostMapping("buildConfirmationEmail")
    public String buildConfirmationEmail(@RequestBody ConfirmationEmailRequest request) {

        String firstName = request.getFirstName();
        String username = request.getUsername();
        String token = request.getToken();

         return emailSender.buildConfirmationEmail(firstName, username, token);
    }


    @PostMapping("sendLoanSuccessMsg")
    public String sendLoanSuccessMsg(@RequestBody SendEmailRequest request) {

        String recipient = request.getRecipientEmail();
        String subject = request.getRecipientSubject();
        String msg = request.getEmailText();

        return emailSender.sendLoanSuccessMsg(recipient, subject, msg);
    }


    @PostMapping("sesendBocoCheckMsgnd")
    public String sendBocoCheckMsg(@RequestBody SendEmailRequest request) {

        String recipient = request.getRecipientEmail();
        String subject = request.getRecipientSubject();
        String msg = request.getEmailText();

        return emailSender.sendBocoCheckMsg(recipient, subject, msg);
    }


    @PostMapping("sendBmAssignLoMsg")
    public String sendBmAssignLoMsg(@RequestBody SendEmailRequest request) {

        String recipient = request.getRecipientEmail();
        String subject = request.getRecipientSubject();
        String msg = request.getEmailText();

        return emailSender.sendBmAssignLoMsg(recipient, subject, msg);
    }


    @PostMapping("sendClientConfirmationMsg")
    public String sendClientConfirmationMsg(@RequestBody SendEmailRequest request) {

        String recipient = request.getRecipientEmail();
        String subject = request.getRecipientSubject();
        String msg = request.getEmailText();

        return emailSender.sendClientConfirmationMsg(recipient, subject, msg);
    }

    @PostMapping("approvalMessage")
    public String approvalMessage(@RequestBody ApprovalEmailReq request) {

        String recipient = request.getRecipient();
        String msg = request.getMessage();
        String senderName = request.getSenderName();

        return emailSender.approvalMessage(recipient, msg, senderName);
    }

    @PostMapping("sendFinalMeetingMsg")
    public String sendFinalMeetingMsg(@RequestBody ApprovalEmailReq request) {

        String recipient = request.getRecipient();
        String msg = request.getMessage();
        String senderName = request.getSenderName();

        return emailSender.sendFinalMeetingMsg(recipient, msg, senderName);
    }

    @PostMapping("sendScheduleMeetingMsg")
    public String sendScheduleMeetingMsg(@RequestBody ApprovalEmailReq request) {

        String recipient = request.getRecipient();
        String msg = request.getMessage();
        String senderName = request.getSenderName();

        return emailSender.sendScheduleMeetingMsg(recipient, msg, senderName);
    }



    @PostMapping("limitExceeded")
    public String limitExceeded(@RequestBody ApprovalEmailReq request) {

        String recipient = request.getRecipient();
        String msg = request.getMessage();
        String senderName = request.getSenderName();

        return emailSender.limitExceeded(recipient, msg, senderName);
    }


}
