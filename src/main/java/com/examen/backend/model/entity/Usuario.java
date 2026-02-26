package com.examen.backend.model.entity;

import java.time.LocalDateTime;

public class Usuario {

    private Long usurId;
    private String usurName;
    private String usurLast;
    private String usurMail;
    private String usurPassword;
    private byte[] usurImg;
    private String usurToken;
    private Boolean usurIsDeleted;
    private LocalDateTime createdAt;

    public Usuario() {}

    public Long getUsurId() {
        return usurId;
    }

    public String getUsurName() {
        return usurName;
    }

    public String getUsurLast() {
        return usurLast;
    }

    public String getUsurMail() {
        return usurMail;
    }

    public String getUsurPassword() {
        return usurPassword;
    }

    public byte[] getUsurImg() {
        return usurImg;
    }

    public String getUsurToken() {
        return usurToken;
    }

    public Boolean getUsurIsDeleted() {
        return usurIsDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setUsurId(Long usurId) {
        this.usurId = usurId;
    }

    public void setUsurName(String usurName) {
        this.usurName = usurName;
    }

    public void setUsurLast(String usurLast) {
        this.usurLast = usurLast;
    }

    public void setUsurMail(String usurMail) {
        this.usurMail = usurMail;
    }

    public void setUsurPassword(String usurPassword) {
        this.usurPassword = usurPassword;
    }

    public void setUsurImg(byte[] usurImg) {
        this.usurImg = usurImg;
    }

    public void setUsurToken(String usurToken) {
        this.usurToken = usurToken;
    }

    public void setUsurIsDeleted(Boolean usurIsDeleted) {
        this.usurIsDeleted = usurIsDeleted;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
