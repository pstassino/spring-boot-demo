package com.panos.demo.domain;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
	
	private final Long id;
    private final String firstname;
    private final String surname;
    private final Calendar dateOfBirth;
    private Date timestamp;

    public User(final Long id, 
    		final String firstname, 
    		final String surname, 
    		final Calendar dateOfBirth,
    		final Date timestamp) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

	public String getSurname() {
        return surname;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }
    
    public Date getTimestamp() {
		return timestamp;
	}
}
