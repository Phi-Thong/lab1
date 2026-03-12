package com.poly.dao;

import com.poly.ennity.J6user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<J6user, String> {

}