package br.com.apitestesjunitmockito.services.impl;

import br.com.apitestesjunitmockito.domain.User;
import br.com.apitestesjunitmockito.repositories.UserRepository;
import br.com.apitestesjunitmockito.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElse(null);
    }
}
