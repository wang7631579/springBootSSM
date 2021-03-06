package com.dha.dhawebdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dha.dhawebdemo.entity.User;
import com.dha.dhawebdemo.service.UserService;


@RestController
@RequestMapping("cache")
public class CacheController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/addUser")
	@Cacheable(value = "cache1", key = "#root.methodName+'['+#userName+']'")
	public User addUser(String userName) {
		// @Cacheable
		// @CachePut
		// @CacheEvict
		// @Caching

		User user = userService.getUserById(10L);
		return user;
	}

}
