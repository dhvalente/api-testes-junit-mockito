package br.com.apitestesjunitmockito.resources;

import br.com.apitestesjunitmockito.domain.User;
import br.com.apitestesjunitmockito.records.UserRecord;
import br.com.apitestesjunitmockito.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    UserServiceImpl userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserRecord> findById(@PathVariable Integer id){
        User user = userService.findById(id);
        UserRecord userRecord = new UserRecord(
                user.getId(), user.getName(),
                user.getEmail(), user.getPassword());
        return ResponseEntity.ok().body(userRecord);
    }
}
