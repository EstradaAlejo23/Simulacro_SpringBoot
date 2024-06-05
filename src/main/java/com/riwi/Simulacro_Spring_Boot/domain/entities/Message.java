package com.riwi.Simulacro_Spring_Boot.domain.entities;

import java.time.LocalDateTime;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity (name = "Messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime date;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Sender",referencedColumnName = "id")
    private User senderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Receiver",referencedColumnName = "id")
    private User receiverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coursesId",referencedColumnName = "id")
    private Course courses;
}
