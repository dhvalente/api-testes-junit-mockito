package br.com.apitestesjunitmockito.services.impl;

import br.com.apitestesjunitmockito.domain.User;
import br.com.apitestesjunitmockito.exceptions.DataIntegratyViolationException;
import br.com.apitestesjunitmockito.exceptions.ObjectNotFoundException;
import br.com.apitestesjunitmockito.records.UserRecord;
import br.com.apitestesjunitmockito.repositories.UserRepository;
import br.com.apitestesjunitmockito.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(UserRecord userRecord) {
        findByEmail(userRecord);
        User user = new User(null , userRecord.name() , userRecord.email(), userRecord.password());
        return userRepository.save(user);
    }


    @Override
    public User findById(Integer id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private void findByEmail(UserRecord userRecord){
        Optional<User> user = userRepository.findByEmail(userRecord.email());
        if(user.isPresent()){
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema.");
        }
    }
}
