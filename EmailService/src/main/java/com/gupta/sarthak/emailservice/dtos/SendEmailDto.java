package com.gupta.sarthak.emailservice.dtos;

public class SendEmailDto {
    private String to;
    private String subject;
    private String body;
    private String from;

    public SendEmailDto(String to, String subject, String body, String from) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.from = from;
    }

    public SendEmailDto() {

    }

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
}
