package br.com.apitestesjunitmockito.resources;

import br.com.apitestesjunitmockito.domain.User;
import br.com.apitestesjunitmockito.records.UserRecord;
import br.com.apitestesjunitmockito.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class UserResourceTest {

    @InjectMocks
    private UserResource userResource;

    @Mock
    private UserServiceImpl userService;
    public static final int ID = 1;
    public static final String NAME = "Diogo";
    public static final String EMAIL = "diogo@hotmail.com";
    public static final String PASSWORD = "123";

    public static final String EXCEPTION_MESSAGE = "Objeto não encontrado!";

    public static final int INDEX = 0;

    public static final String EMAIL_EXCEPTION_MESSAGE = "E-mail já cadastrado no sistema.";
    private User user;
    private UserRecord userRecord;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSucess() {
        when(userService.findById(anyInt())).thenReturn(user);
        ResponseEntity<UserRecord> response = userResource.findById(ID);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class , response.getClass());
        assertEquals(UserRecord.class , response.getBody().getClass());
        assertEquals(ID , response.getBody().id());
        assertEquals(NAME , response.getBody().name());
        assertEquals(EMAIL , response.getBody().email());
        assertEquals(PASSWORD , response.getBody().password());
    }

    @Test
    void whenFindAllTheReturnAListOfUserRecord() {
        when(userService.findAll()).thenReturn(List.of(user));
        ResponseEntity<List<UserRecord>> response = userResource.findAll();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class , response.getClass());
        assertEquals(UserRecord.class , response.getBody().get(0).getClass());
        assertEquals(ID, response.getBody().get(0).id());
        assertEquals(NAME, response.getBody().get(0).name());
        assertEquals(EMAIL, response.getBody().get(0).email());
        assertEquals(PASSWORD, response.getBody().get(0).password());
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(userService.create(any())).thenReturn(user);
        ResponseEntity<UserRecord> response  = userResource.create(userRecord);
        assertEquals(ResponseEntity.class , response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(userService.update(ID, userRecord)).thenReturn(user);
        ResponseEntity<UserRecord> response = userResource.update(ID, userRecord);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class , response.getClass());
        assertEquals(UserRecord.class , response.getBody().getClass());

        assertEquals(ID , response.getBody().id());
        assertEquals(NAME , response.getBody().name());
        assertEquals(EMAIL , response.getBody().email());

    }

    @Test
    void whenDeleteThenReturnSucess() {
        doNothing().when(userService).delete(anyInt());
        ResponseEntity<UserRecord> response = userResource.delete(ID);
        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(userService, times(1)).delete(anyInt());
        assertEquals(HttpStatus.NO_CONTENT , response.getStatusCode());
    }

    private void startUser(){
        user =  new User(ID, NAME, EMAIL, PASSWORD);
        userRecord =  new UserRecord(ID, NAME, EMAIL, PASSWORD);
    }
}