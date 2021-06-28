package com.commercity.monolith.datafetchers;

import com.commercity.monolith.models.User;
import com.commercity.monolith.services.user.UserService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
@Slf4j
public class UserDataFetcher {

    private UserService userService;

    @DgsQuery(field = "user")
    public List<User> getUsers(@InputArgument("id") String id, @InputArgument("email") String email) {
        log.info("received request to get users ID: "+id+ " EMAIL: "+email);
        return userService.getUsers();
    }

    @DgsMutation(field = "registerUser")
    public User createUser(@InputArgument("user") User user) {
        log.info("received request to create user email: "+user.getEmail()+ " password: "+user.getPassword());
        return userService.createUser(user);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
