package com.ensaj.comptes.demo.spring.controllers;

import com.ensaj.comptes.demo.spring.entities.Compte;
import com.ensaj.comptes.demo.spring.reposteries.CompteRepostery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banque")
public class CompteController {
    @Autowired
    private CompteRepostery compteRepostery;

    @GetMapping(value="/comptes", produces={"application/json", "application/xml"})
    public List<Compte> getComptes(){
        return compteRepostery.findAll();
    }
    @GetMapping(value="/comptes/{id}", produces={"application/json", "application/xml"})
    public ResponseEntity<Compte> getCompte(@PathVariable int id){
        return compteRepostery.findById(id)
                .map(compte -> ResponseEntity.ok().body(compte))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping(value="/comptes", consumes={"application/json", "application/xml"}, produces={"application/json", "application/xml"})
    public Compte createCompte(@RequestBody Compte compte){
        return compteRepostery.save(compte);
    }
    @PutMapping(value="/comptes/{id}", consumes={"application/json", "application/xml"}, produces={"application/json", "application/xml"})
    public ResponseEntity<Compte> updateCompte(@PathVariable int id, @RequestBody Compte compteDetails){
        return compteRepostery.findById(id)
                .map(compte -> {
                    compte.setSolde(compteDetails.getSolde());
                    compte.setDateCreation(compteDetails.getDateCreation());
                    compte.setType(compteDetails.getType());
                    Compte updatedCompte = compteRepostery.save(compte);
                    return ResponseEntity.ok().body(updatedCompte);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping(value = "/comptes/{id}", produces={"application/json", "application/xml"})
    public ResponseEntity<Void> deleteCompte(@PathVariable int id){
        return compteRepostery.findById(id)
                .map(compte -> {
                    compteRepostery.delete(compte);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
