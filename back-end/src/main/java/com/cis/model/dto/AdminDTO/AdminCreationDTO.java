package com.cis.model.dto.AdminDTO;

import com.cis.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminCreationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private String email;
    private String password;

    public AdminCreationDTO(Admin admin) {
        this.name = admin.getName();
        this.phone = admin.getPhone();
        this.email = admin.getEmail();
        this.password = admin.getPassword();
    }
}
