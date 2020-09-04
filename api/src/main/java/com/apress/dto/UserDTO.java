package com.apress.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseDTO {

	private Long id;

	@JsonProperty("full_name")
	private String fullName;

	private String mobile;

	private String email;

	private String role;

	private String password;

}
