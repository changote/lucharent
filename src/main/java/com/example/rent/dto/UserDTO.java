package com.example.rent.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	@JsonProperty("username")
	private String username;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("email")
	private String email;

	@JsonProperty("whenCreated")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate whenCreated;

	@JsonProperty("uid")
	private Long userId;
}