package br.com.apitestesjunitmockito.resources;

import br.com.apitestesjunitmockito.domain.User;
import br.com.apitestesjunitmockito.records.UserRecord;
import br.com.apitestesjunitmockito.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<UserRecord>> findAll (){
        List<UserRecord> listRecord = userService.findAll().stream().map(
                x -> new UserRecord(
                        x.getId(), x.getName(),
                        x.getEmail(), x.getPassword())).toList();
        return ResponseEntity.ok().body(listRecord);
    }

    @PostMapping
    public ResponseEntity<UserRecord> create (@RequestBody UserRecord userRecord){
        User obj = userService.create(userRecord);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRecord> update (@PathVariable Integer id, @RequestBody UserRecord userRecord){
        User user = userService.update(id,new UserRecord(id, userRecord.name(), userRecord.email(), userRecord.password()));
        return ResponseEntity.ok().body(new UserRecord(id, user.getName(), user.getEmail(), user.getPassword()));
    }
}
