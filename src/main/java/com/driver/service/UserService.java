package com.driver.service;

import java.util.List;

import com.driver.shared.dto.UserDto;
import org.springframework.stereotype.Repository;

/**
 * Handle exception cases for all methods which throw Exception
 */
@Repository
public interface UserService{

	UserDto createUser(UserDto user) throws Exception;
	UserDto getUser(String email) throws Exception;
	UserDto getUserByUserId(String userId) throws Exception;
	UserDto updateUser(String userId, UserDto user) throws Exception;
	void deleteUser(String userId) throws Exception;
	List<UserDto> getUsers();
}
