package com.hatio.todoapplication.dto;


import com.hatio.todoapplication.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @Enumerated(value = EnumType.STRING)
    Role role;
}
