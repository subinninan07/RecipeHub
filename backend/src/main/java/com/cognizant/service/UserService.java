package com.cognizant.service;

import java.util.List;
import java.util.Set;

import com.cognizant.exception.UserException;
import com.cognizant.model.User;

public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;

}
