package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    boolean addUser(User user);

    User findByUsername(String username);

    User getUserById(Long id);

    void updateUser(Long id, User user);

    void deleteUser(Long id);
}
