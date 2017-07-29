package com.vbhoomidi.entity;

import java.io.Serializable;

/**
 * Created by vikramreddy on 7/13/2017.
 */
public class Email implements Serializable{
    private String senderEmail;
   private String receiverEmail;
   private String emailMessage;

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }
}
