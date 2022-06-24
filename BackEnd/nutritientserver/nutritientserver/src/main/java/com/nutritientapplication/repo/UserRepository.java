package com.nutritientapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutritientapplication.entity.User;

/*Basically UserRepository is uses the entity user and does all the repository works like adding deleting etc. of user data.*/
/*Basically UserRepository contains all the methods to save or delete data. Basically it will interact with entity or database*/
/*Basically it works as a class to fetch data from database*/
public interface UserRepository extends JpaRepository<User,Integer> {

	User findByUsername(String userName);

}
