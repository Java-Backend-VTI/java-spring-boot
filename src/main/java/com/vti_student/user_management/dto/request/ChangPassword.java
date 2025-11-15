package com.vti_student.user_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangPassword {
    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Pattern(regexp = "^[A-Za-z0-9._-]{3,50}$", message = "Username can contain letters, numbers, dot, underscore and hyphen")
    private String username;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String oldPassword;

    @NotBlank(message = "New password must not be blank")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String newPassword;
}
