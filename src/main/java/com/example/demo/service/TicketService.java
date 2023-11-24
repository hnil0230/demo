package com.example.demo.service;

import com.example.demo.entity.TicketEntity;
import com.example.demo.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<TicketEntity> getTicketsByMno(Long userId) {
        // 사용자 ID를 기반으로 티켓 목록을 조회하는 로직
        return ticketRepository.findByMno(userId);
    }
}
