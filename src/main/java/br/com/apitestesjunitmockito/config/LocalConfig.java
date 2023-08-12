package br.com.apitestesjunitmockito.config;

import br.com.apitestesjunitmockito.domain.User;
import br.com.apitestesjunitmockito.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    UserRepository userRepository;

    @Bean
    public void startDB(){
        User u1 = new User(null , "Diogo" , "diogo@hotmail.com" , "123");
        User u2 = new User(null , "Andre" , "andre@hotmail.com" , "321");
        userRepository.saveAll(List.of(u1, u2));
    }
}
