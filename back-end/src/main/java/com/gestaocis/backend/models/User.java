package com.gestaocis.backend.models;

import com.gestaocis.backend.utils.enums.Role;
import com.gestaocis.backend.utils.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Column
    private String professionalDocument;

    @Column(nullable = false)
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User: {");
        sb.append("id=").append(id);
        sb.append(", uuid=").append(uuid);
        sb.append(", role=").append(role.toString());
        sb.append(", specialty=").append(professionalDocument);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", cpf=").append(rg);
        sb.append(", fullName=").append(fullName);
        sb.append(", password=").append(password);
        sb.append("}");
        return sb.toString();
    }
}


