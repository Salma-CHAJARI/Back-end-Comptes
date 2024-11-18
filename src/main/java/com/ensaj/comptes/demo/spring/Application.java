package com.ensaj.comptes.demo.spring;

import com.ensaj.comptes.demo.spring.entities.Compte;
import com.ensaj.comptes.demo.spring.entities.TypeCompte;
import com.ensaj.comptes.demo.spring.reposteries.CompteRepostery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	CommandLineRunner start(CompteRepostery compteRepostery) {
		return args -> {
			compteRepostery.save(new Compte(null,Math.random()*9000, new Date(), TypeCompte.COURANT));
			compteRepostery.save(new Compte(null,Math.random()*9000, new Date(), TypeCompte.EPARGNE));
			compteRepostery.save(new Compte(null,Math.random()*9000, new Date(), TypeCompte.COURANT));
			compteRepostery.findAll().forEach(c ->{
				System.out.println(c.toString());
			});
		};
	}

}
