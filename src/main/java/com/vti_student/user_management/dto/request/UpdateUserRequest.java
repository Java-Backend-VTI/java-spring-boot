package com.vti_student.user_management.dto.request;

import java.util.Date;

import com.vti_student.user_management.common.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    @NotBlank(message = "First name must not be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-zÀ-ỹ\\s]+$", message = "First name can only contain letters and spaces")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-zÀ-ỹ\\s]+$", message = "Last name can only contain letters and spaces")
    private String lastName;

    @NotNull(message = "Birthday is required")
    @Past(message = "Birthday must be a date in the past")
    private Date birthday;

    @NotBlank(message = "Address must not be blank")
    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
    private String address;

    @NotNull(message = "Role is required")
    private UserRole role;
}
