package com.example.cs4520_inclass_rishabh0516.inClass08;
// Rishabh Sahu
// Assignment #8

public class Message {
    private String senderEmail;
    private String receiverEmail;
    private String message;
    private long timestamp;
    private String senderName;

    public Message() {
    }

    public Message(String senderEmail, String senderName, String receiverEmail, String message, long timestamp) {
        this.senderEmail = senderEmail;
        this.senderName = senderName;
        this.receiverEmail = receiverEmail;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderName() {
        return senderName;
    }

    public long getTimestamp() {
        return timestamp;
    }


    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
