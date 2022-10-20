package com.Pharmacia.crud.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.Pharmacia.crud.service.UserService;

public class EmailExistValidator implements ConstraintValidator<EmailExist, String>{
	
	private String email;
	
	@Autowired
	@Qualifier("servicesImpl")
	private UserService userService;


    @Override
    public void initialize(EmailExist e) {
    	email = e.email();
            
    }


	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;//userService.confirmExisting(email);
	}


	
	


}
