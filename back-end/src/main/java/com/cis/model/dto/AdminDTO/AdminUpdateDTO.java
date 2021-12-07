package com.cis.model.dto.AdminDTO;

import com.cis.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private String phone;
    private String email;

    public AdminUpdateDTO(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.phone = admin.getPhone();
        this.email = admin.getEmail();
    }
}
