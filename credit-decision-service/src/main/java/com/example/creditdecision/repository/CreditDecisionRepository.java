package com.example.creditdecision.repository;


import com.example.creditapp.model.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditDecisionRepository extends JpaRepository<CreditApplication, Long> {
}