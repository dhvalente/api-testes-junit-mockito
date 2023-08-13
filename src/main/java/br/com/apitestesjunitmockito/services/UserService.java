package br.com.apitestesjunitmockito.services;

import br.com.apitestesjunitmockito.domain.User;
import br.com.apitestesjunitmockito.records.UserRecord;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserRecord userRecord);

    User update(Integer id , UserRecord userRecord);

    void delete(Integer id);
}
