package com.machi.springbootweb.repository;

import com.machi.springbootweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by dell on 2018/2/7.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByName(String name);

    List<User> findAll();

}
