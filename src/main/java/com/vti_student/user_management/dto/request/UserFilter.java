package com.vti_student.user_management.dto.request;

import com.vti_student.user_management.common.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter {
    private String firstName;
    private String lastName;
    private String address;
    private UserRole role;
}
