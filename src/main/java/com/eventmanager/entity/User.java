package com.eventmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
@Column(nullable = false, unique = true)
    private String login;  //  musi być unikalny
    @Column(nullable = false)
    private String password;  //  musi byc zabezpieczone
    @Column(name = "display_name", nullable = false)
    private String displayName;
    @Column(nullable = false)
    private String role; //  Role- np user albo admin


}
