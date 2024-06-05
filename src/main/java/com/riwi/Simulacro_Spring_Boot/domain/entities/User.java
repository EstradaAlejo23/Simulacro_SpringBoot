package com.riwi.Simulacro_Spring_Boot.domain.entities;

import java.util.List;


import com.riwi.Simulacro_Spring_Boot.util.RoleUsers;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity (name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String name;
    @Column(length = 30)
    private String password;
    @Column(length = 40)
    private String email;
    @Column(length = 50)
    private String fullName;
    @Enumerated(EnumType.STRING)
    private RoleUsers role;


    @ToString.Exclude
    @OneToMany(mappedBy = "instructor_id",
    cascade = CascadeType.ALL)
    private List<Course> courses;

    @ToString.Exclude
    @OneToMany(mappedBy = "courseEnrollsments",cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    @ToString.Exclude
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Submission> submissions;

    @ToString.Exclude
    @OneToMany(mappedBy = "senderId",cascade = CascadeType.ALL)
    private List<Message> messagesSender;

    @ToString.Exclude
    @OneToMany(mappedBy = "receiverId",cascade = CascadeType.ALL)
    private List<Message> messagesReceiver;

}