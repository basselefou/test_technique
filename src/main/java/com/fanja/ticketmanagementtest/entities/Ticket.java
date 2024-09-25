package com.fanja.ticketmanagementtest.entities;

import com.fanja.ticketmanagementtest.enums.StatutTicket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tickettest")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="TITRE")
    private String titre;

    @Column(name="DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUT_TICKET")
    private StatutTicket statutTicket;



    @ManyToOne
    @JoinColumn(name = "iduser", nullable = true)
    private User user;
}
