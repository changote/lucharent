package com.example.rent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="rent_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private Long userId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name="first_name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name="email_address")
	private String email;

	@Column(name="phone")
	private String phone;

	@Column(name="photo")
	private String photo;
}