package com.itwill.spring3.dto;

import lombok.Data;

@Data
public class MemberSignUpDto {
    
    private String username;
    private String password;
    private String email;

}