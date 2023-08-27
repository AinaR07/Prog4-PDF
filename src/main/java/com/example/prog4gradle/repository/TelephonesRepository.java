package com.example.prog4gradle.repository;

import com.example.prog4gradle.model.Telephones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TelephonesRepository extends JpaRepository<Telephones, Long> {
    Telephones findByNumero(String numero);

    Telephones findByPhoneNumberIdentifier(String findByPhoneNumberIdentifier);


}
