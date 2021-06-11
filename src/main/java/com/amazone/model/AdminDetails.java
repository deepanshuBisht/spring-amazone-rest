package com.amazone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="amazoneadmin")
public class AdminDetails {

	@Id
	@Column(name="adminid")
	private String adminId;
	private String name;
	private String password;
	@Column(name="mailid")
	private String mailId;
	@Column(name="mobileno")
	private long mobileNo;
	private String image;
	
}
