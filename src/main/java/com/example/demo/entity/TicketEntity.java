package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "ticket")//database에 해당 이름의 테이블 생성
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticket_id;
    @Column
    private Long mno;
    @Column
    private String ticket_seat_num;
    @Column
    private Integer ticket_price;
    @Column
    private Integer sid;

    public static TicketEntity toTicketEntity(TicketEntity ticketEnt){
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTicket_seat_num(ticketEnt.getTicket_seat_num());
        ticketEntity.setTicket_price(ticketEnt.getTicket_price());
        ticketEntity.setMno(ticketEntity.getMno());
        ticketEntity.setSid(ticketEntity.getSid());

        return ticketEntity;
    }
}
