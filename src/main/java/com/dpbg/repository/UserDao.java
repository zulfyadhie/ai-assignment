package com.dpbg.repository;

import com.dpbg.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by zulfy on 9/12/16.
 */
public interface UserDao extends CrudRepository<User, Long>{

    User findOneByUsername(String username);
    User findOneByEmailAndActivationCode(String email, String activationCode);
    User findOneByEmail(String email);

}