package com.ensaj.comptes.demo.spring.reposteries;

import com.ensaj.comptes.demo.spring.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompteRepostery extends JpaRepository<Compte, Integer> {
    List<Compte> id(Long id);
}
