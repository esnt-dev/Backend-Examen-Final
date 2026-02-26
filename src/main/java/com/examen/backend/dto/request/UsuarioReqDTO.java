package com.examen.backend.dto.request;

public class UsuarioReqDTO {

    private String name;
    private String last;
    private String mail;
    private String password;
    private String imgBase64;

    public UsuarioReqDTO() {}

    public UsuarioReqDTO(String name, String last, String mail, String password, String imgBase64) {}

    public String getName() {
        return name;
    }

    public String getLast() {
        return last;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public static UsuarioReqDTO forLogin(String mail, String password) {
        UsuarioReqDTO req= new UsuarioReqDTO();
        req.setMail(mail);
        req.setPassword(password);
        return req;
    }

    public static UsuarioReqDTO forRegister(String name, String last, String mail, String password, String imgBase64) {
        UsuarioReqDTO req = new UsuarioReqDTO();
        req.setName(name);
        req.setLast(last);
        req.setMail(mail);
        req.setPassword(password);
        req.setImgBase64(imgBase64);
        return req;
    }

}
