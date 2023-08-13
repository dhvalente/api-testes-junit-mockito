package br.com.apitestesjunitmockito.services;

import br.com.apitestesjunitmockito.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
