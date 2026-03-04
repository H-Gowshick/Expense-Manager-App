package com.project.expence_tracker.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {

	private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
   
}
