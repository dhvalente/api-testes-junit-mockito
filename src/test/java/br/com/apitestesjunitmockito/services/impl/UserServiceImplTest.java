package br.com.apitestesjunitmockito.services.impl;

import br.com.apitestesjunitmockito.domain.User;
import br.com.apitestesjunitmockito.exceptions.DataIntegratyViolationException;
import br.com.apitestesjunitmockito.exceptions.ObjectNotFoundException;
import br.com.apitestesjunitmockito.records.UserRecord;
import br.com.apitestesjunitmockito.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final int ID = 1;
    public static final String NAME = "Diogo";
    public static final String EMAIL = "diogo@hotmail.com";
    public static final String PASSWORD = "123";
    public static final String EXCEPTION_MESSAGE = "Objeto não encontrado!";
    public static final int INDEX = 0;
    public static final String EMAIL_EXCEPTION_MESSAGE = "E-mail já cadastrado no sistema.";

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    private UserRecord userRecord;

    private Optional<User> optinalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenCreateThenReturnSucess() {
        when(userRepository.save(any())).thenReturn(user);
        User response = userService.create(userRecord);
        assertNotNull(response);
        assertEquals(User.class , response.getClass());
        assertEquals(ID , response.getId());
        assertEquals(NAME , response.getName());
        assertEquals(EMAIL , response.getEmail());
        assertEquals(PASSWORD , response.getPassword());
    }
    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optinalUser);
        try{
            optinalUser.get().setId(4);
            userService.create(userRecord);
        }catch (Exception ex){
            assertEquals(DataIntegratyViolationException .class , ex.getClass());
            assertEquals(EMAIL_EXCEPTION_MESSAGE, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findById(Mockito.anyInt())).thenReturn(optinalUser);
        User response = userService.update(ID,userRecord);
        assertNotNull(response);
        assertEquals(User.class , response.getClass());
        assertEquals(ID , response.getId());
        assertEquals(NAME , response.getName());
        assertEquals(EMAIL , response.getEmail());
        assertEquals(PASSWORD , response.getPassword());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optinalUser);
        try{
            optinalUser.get().setId(4);
            userService.update(ID,userRecord);
        }catch (Exception ex){
            assertEquals(DataIntegratyViolationException .class , ex.getClass());
            assertEquals(EMAIL_EXCEPTION_MESSAGE, ex.getMessage());
        }
    }

    @Test
    void deleteWithSucess() {
        when(userRepository.findById(anyInt())).thenReturn(optinalUser);
        doNothing().when(userRepository).deleteById(anyInt());
        userService.delete(ID);
        verify(userRepository, times(1)).deleteById(anyInt());
    }
    @Test
    void deleteWithObjectNotFoundException(){
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(EXCEPTION_MESSAGE));
        try{
            userService.delete(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class , ex.getClass());
            assertEquals(EXCEPTION_MESSAGE , ex.getMessage());
        }
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(userRepository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(EXCEPTION_MESSAGE));
        try{
            userService.findById(ID);
        }catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundException.class , ex.getClass());
            Assertions.assertEquals(EXCEPTION_MESSAGE, ex.getMessage());
        }
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(Mockito.anyInt())).thenReturn(optinalUser);
        User response = userService.findById(ID);
        assertNotNull(response);
        Assertions.assertEquals(User.class , response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void findAll() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> response = userService.findAll();
        assertNotNull(response);
        assertEquals(1 , response.size());
        assertEquals(User.class , response.get(INDEX).getClass());
        assertEquals(ID , response.get(INDEX).getId());
        assertEquals(NAME , response.get(INDEX).getName());
        assertEquals(EMAIL , response.get(INDEX).getEmail());
        assertEquals(PASSWORD , response.get(INDEX).getPassword());
    }

    private void startUser(){
        user =  new User(ID, NAME, EMAIL, PASSWORD);
        userRecord =  new UserRecord(ID, NAME, EMAIL, PASSWORD);
        optinalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}