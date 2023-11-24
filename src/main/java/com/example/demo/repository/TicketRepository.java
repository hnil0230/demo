package com.example.demo.repository;

import com.example.demo.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    List<TicketEntity> findByMno(Long mno);
}
