package com.bibro.repository;

import com.bibro.domain.user.RegistrationToken;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationTokenRepository extends CrudRepository<RegistrationToken, Integer> {
    RegistrationToken findByToken(String token);
}
