package com.emazon.user.domain.api.usecase;

import com.emazon.user.domain.api.UserServicePort;
import com.emazon.user.domain.exceptions.UserDocumentAlreadyExistsException;
import com.emazon.user.domain.exceptions.UserEmailAlreadyExistsException;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.spi.UserPersistencePort;
import com.emazon.user.domain.utils.RoleName;

import static com.emazon.user.domain.utils.ValidationUtils.validateBirthDate;

public class UserUseCase implements UserServicePort {

    private final UserPersistencePort userPersistencePort;

    public UserUseCase(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    private void createUser(User user) {
        validateBirthDate(user.getBirthdate());
        if(userPersistencePort.userExistsByEmail(user.getEmail())) throw new UserEmailAlreadyExistsException(user.getEmail());
        if(userPersistencePort.userExistsByIdentityDocument(user.getIdentityDocument())) throw new UserDocumentAlreadyExistsException(user.getIdentityDocument());
        userPersistencePort.createUser(user);
    }

    @Override
    public void createWarehouseAssistant(User user) {
        user.setRole(new Role(1L, RoleName.WAREHOUSE_ASSISTANT));
        createUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email);
    }
}