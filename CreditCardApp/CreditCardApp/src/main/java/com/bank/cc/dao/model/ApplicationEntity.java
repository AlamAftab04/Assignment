package com.bank.cc.dao.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Application")
public class ApplicationEntity {

	@Id
	private long applicationId;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String email;
	private String ssn;
	@CreatedDate
	@Temporal(TemporalType.DATE)
	private Date submissionDate;
}
