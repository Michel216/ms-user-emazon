package com.emazon.user.domain.spi;

import com.emazon.user.domain.model.User;

import java.util.List;

public interface UserPersistencePort {
    void createUser(User user);
    boolean userExistsByIdentityDocument(String identityDocument);
    boolean userExistsByEmail(String email);
}
