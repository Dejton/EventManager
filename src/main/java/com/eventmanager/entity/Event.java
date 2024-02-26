package com.eventmanager.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;
    private String title;
    @Column(name = "starting_date")
    private Date startingDate;
    @Column(name = "ending_date")
    private Date endingDate;
    private String description;
    @Column(name = "organizer_id")
    private long organizerId;   //   klucz obcy od User wiele do jednego

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(long organizerId) {
        this.organizerId = organizerId;
    }
}
