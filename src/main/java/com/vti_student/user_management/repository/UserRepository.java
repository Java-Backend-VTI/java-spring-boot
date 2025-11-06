package com.vti_student.user_management.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vti_student.user_management.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    List<User> findByFirstName(String firstName);

    List<User> findByFirstNameContainingIgnoreCase(String firstName);

    List<User> findByLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(String firstName, String lastName);

    List<User> findByBirthdayBetween(Date fromDate, Date toDate);

    @Query("SELECT u FROM User u WHERE u.birthday BETWEEN ?1 AND ?2")
    List<User> findByBirthdayBetweenHQL(Date fromDate, Date toDate);

    @Query(value = "SELECT * FROM users WHERE birthday BETWEEN :fromDate AND :toDate", nativeQuery = true)
    List<User> findByBirthdayBetweenSQL(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
