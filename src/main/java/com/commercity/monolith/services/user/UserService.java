package com.commercity.monolith.services.user;

import com.commercity.monolith.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface UserService {
    public List<User> getUsers();
    public User createUser(User user);
}
