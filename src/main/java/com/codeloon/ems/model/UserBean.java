package com.codeloon.ems.model;

import com.codeloon.ems.entity.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBean {

    private String id;

    private String username;

    private String password;

    private String email;

    private String position;

    private String address;

    private String mobileNo;

    private Boolean enabled = true;

    private Boolean accountNonExpired = true;

    private Boolean credentialsNonExpired = true;

    private Boolean accountNonLocked = true;

    private LocalDateTime createdAt;

    private Set<Role> roles;
}
