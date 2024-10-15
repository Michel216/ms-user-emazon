package com.emazon.user.domain.model;

import com.emazon.user.domain.utils.RoleName;

public class Role {
    private Long id;
    private RoleName name;

    public Role(Long id, RoleName name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
