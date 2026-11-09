package com.vipin.userinfo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vipin.userinfo.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{

}
