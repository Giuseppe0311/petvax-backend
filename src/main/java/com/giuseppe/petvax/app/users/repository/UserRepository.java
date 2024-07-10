package com.giuseppe.petvax.app.users.repository;

import com.giuseppe.petvax.app.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer>{
}
