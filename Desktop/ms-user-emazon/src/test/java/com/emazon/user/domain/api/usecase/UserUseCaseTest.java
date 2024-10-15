package com.emazon.user.domain.api.usecase;

import com.emazon.user.domain.exceptions.UnderAgeException;
import com.emazon.user.domain.exceptions.UserDocumentAlreadyExistsException;
import com.emazon.user.domain.exceptions.UserEmailAlreadyExistsException;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.spi.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createWarehouseAssistant() {
        User user = new User("", "name", "lastname", "0000000000", LocalDateTime.of(1,1,1,1,1,1), "+5555555555555", "email@email.com", "password", null);
        when(userPersistencePort.userExistsByEmail(any())).thenReturn(false);
        when(userPersistencePort.userExistsByIdentityDocument(any())).thenReturn(false);
        userUseCase.createWarehouseAssistant(user);
        verify(userPersistencePort).createUser(any());
    }

    @Test
    void createWarehouseAssistant_EmailAlreadyExists() {
        User user = new User("", "name", "lastname", "0000000000", LocalDateTime.of(1,1,1,1,1,1), "+5555555555555", "email@email.com", "password", null);
        when(userPersistencePort.userExistsByEmail(any())).thenReturn(true);
        when(userPersistencePort.userExistsByIdentityDocument(any())).thenReturn(false);
        assertThrows(UserEmailAlreadyExistsException.class,
                () -> userUseCase.createWarehouseAssistant(user));
        verify(userPersistencePort, times(0)).createUser(any());
    }

    @Test
    void createWarehouseAssistant_DocumentAlreadyExists() {
        User user = new User("", "name", "lastname", "0000000000", LocalDateTime.of(1,1,1,1,1,1), "+5555555555555", "email@email.com", "password", null);
        when(userPersistencePort.userExistsByEmail(any())).thenReturn(false);
        when(userPersistencePort.userExistsByIdentityDocument(any())).thenReturn(true);
        assertThrows(UserDocumentAlreadyExistsException.class,
                () -> userUseCase.createWarehouseAssistant(user));
        verify(userPersistencePort, times(0)).createUser(any());
    }

    @Test
    void createWarehouseAssistant_UnderAge() {
        User user = new User("", "name", "lastname", "0000000000", LocalDateTime.now(), "+5555555555555", "email@email.com", "password", null);
        when(userPersistencePort.userExistsByEmail(any())).thenReturn(true);
        when(userPersistencePort.userExistsByIdentityDocument(any())).thenReturn(true);
        assertThrows(UnderAgeException.class,
                () -> userUseCase.createWarehouseAssistant(user));
        verify(userPersistencePort, times(0)).createUser(any());
    }
}