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
@Table(name="amazonecart")
public class CartDetails {

	@Column(name="userid")
	private String userId;
	@Id
	@Column(name="productid")
	private int proId;
	private String name;
	private String brand;
	private String category;
	private String description;
	private Double price;
	private String image;
	
}
