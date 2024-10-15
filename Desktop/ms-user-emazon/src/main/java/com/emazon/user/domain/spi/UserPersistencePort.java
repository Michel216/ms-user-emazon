package com.emazon.user.domain.spi;

import com.emazon.user.domain.model.User;

public interface UserPersistencePort {
    void createUser(User user);
    boolean userExistsByIdentityDocument(String identityDocument);
    boolean userExistsByEmail(String email);
    User getUserByEmail(String email);
}