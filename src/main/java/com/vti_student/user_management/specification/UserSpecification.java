package com.vti_student.user_management.specification;

import org.springframework.data.jpa.domain.Specification;

import com.vti_student.user_management.common.UserRole;
import com.vti_student.user_management.model.User;

public class UserSpecification {
    // address;
    // lastName;
    // address;

    public static Specification<User> hasFirstNameLink(String firstName) {
        return (root, query, builder) -> {
            return builder.like(builder.lower(root.get("firstName")), "%" + firstName + "%");
        };
    }

    public static Specification<User> hasLastNameLink(String lastName) {
        return (root, query, builder) -> {
            return builder.like(builder.lower(root.get("lastName")), "%" + lastName + "%");
        };
    }

    public static Specification<User> hasAddressLink(String address) {
        return (root, query, builder) -> {
            return builder.like(builder.lower(root.get("address")), "%" + address + "%");
        };
    }

    public static Specification<User> hasUserRoleLink(UserRole role) {
        return (root, query, builder) -> builder.equal(root.get("role"), role);
    }
}