package com.eventmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "registrations")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private long id;
    @Column(name = "user_id")
    private long userId;   // klucz obcy do User, wiele do wiele
    @Column(name = "event_id")
    private long eventId;   //  klucz obcy do Event, (?)

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
