package com.example.prog4gradle.repository;

import com.example.prog4gradle.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    @Query(value = "SELECT * FROM employee e " +
            "WHERE (:firstName IS NULL OR e.first_name LIKE %:firstName%)" +
            "AND (:lastName IS NULL OR e.last_name LIKE %:lastName%)" +
            "AND (:sexe IS NULL OR e.sexe LIKE %:sexe%)" +
            "AND (:fonction IS NULL OR e.fonction LIKE %:fonction%)",
            nativeQuery = true)
    List<Employee> findEmployeesByFilters(@Param("firstName") String firstName,
                                          @Param("lastName") String lastName,
                                          @Param("sexe") String sexe,
                                          @Param("fonction") String fonction);

    @Query(value = "SELECT DISTINCT e.* FROM employee e " +
            "JOIN employee_telephones et ON e.id = et.employee_id " +
            "JOIN telephones t ON et.telephone_id = t.id " +
            "WHERE t.country_code = :countryCode", nativeQuery = true)
    List<Employee> findAllByCountryCode(@Param("countryCode") String countryCode);


    List<Employee> findByDateEmbaucheBetween(LocalDate startDate, LocalDate endDate);


    List<Employee> findByDateDepartBetween(LocalDate startDate, LocalDate endDate);

}
