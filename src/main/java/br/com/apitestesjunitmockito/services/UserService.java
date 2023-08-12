package br.com.apitestesjunitmockito.services;

import br.com.apitestesjunitmockito.domain.User;

public interface UserService {

    User findById(Long id);
}
