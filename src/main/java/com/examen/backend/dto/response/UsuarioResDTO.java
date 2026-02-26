package com.examen.backend.dto.response;

import com.examen.backend.dto.request.UsuarioReqDTO;

public class UsuarioResDTO {

    private Long id;
    private String name;
    private String last;
    private String mail;
    private String token;
    private String imgBase64;

    public UsuarioResDTO() {}

    public UsuarioResDTO(Long id,String name, String last, String mail, String token, String imgBase64) {
        this.id = id;
        this.name = name;
        this.last = last;
        this.mail = mail;
        this.token = token;
        this.imgBase64 = imgBase64;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLast() { return last; }
    public void setLast(String last) { this.last = last; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPassword() { return token; }
    public void setToken(String password) { this.token = token; }

    public String getImgBase64() { return imgBase64; }
    public void setImgBase64(String imgBase64) { this.imgBase64 = imgBase64; }


    public static UsuarioResDTO forLogin(Long id, String name, String last, String mail, String token, String imgBase64) {
        UsuarioResDTO rep = new UsuarioResDTO();
        rep.setId(id);
        rep.setName(name);
        rep.setLast(last);
        rep.setMail(mail);
        rep.setToken(token);
        rep.setImgBase64(imgBase64);
        return rep;
    }

    public static boolean forRegister(String email) {
        return email != null && !email.isEmpty();
    }

}
