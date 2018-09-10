package ua.ksa.didactic.simplewebjpasecurity.core.dao;


import org.springframework.data.repository.CrudRepository;
import ua.ksa.didactic.simplewebjpasecurity.core.model.User;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}

