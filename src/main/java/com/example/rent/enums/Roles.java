package com.example.rent.enums;

public enum Roles {
    BASIC("ROLE_BASIC","BASIC"),
    ADMIN("ROLE_ADMIN","ADMIN");

    private String rolSeteo;
    private String rolName;

    private Roles(String rolSeteo, String rolName){
        this.rolSeteo = rolSeteo;
        this.rolName = rolName;
    }

    public String getRolSeteo() {
        return rolSeteo;
    }

    public void setRolSeteo(String rolSeteo) {
        this.rolSeteo = rolSeteo;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolNombre) {
        this.rolName = rolNombre;
    }

}
