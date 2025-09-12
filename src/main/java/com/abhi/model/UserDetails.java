package com.abhi.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "SECURITY_USERS")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(length = 20, unique = true, nullable = false)
    private String uname;

    @Column(length = 60, unique = true, nullable = false)
    private String pwd;

    @Column(length = 20, unique = true, nullable = false)
    private String mail;


    private boolean status = true;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "SECURITY_ROLES", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "uid"))
    @Column(name = "role")
    private Set<String> roles;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
