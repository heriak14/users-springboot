package net.proselyte.springbootdemo.service;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.repository.JsonUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JsonUserService implements UserService {

    @Autowired
    private JsonUserRepository userRepository;

    @Override
    public User findById(Long id) {
        return findAll().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user with id: " + id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllUsers();
    }

    @Override
    public User saveUser(User user) {
        List<User> users = new ArrayList<>(userRepository.findAllUsers());
        Optional<User> userToUpdate = users.stream().filter(u -> u.getLogin().equals(user.getLogin())).findFirst();
        if (userToUpdate.isPresent()) {
            updateUser(user, userToUpdate.get());
        } else {
            user.setId((long) users.size());
            users.add(user);
        }
        userRepository.writeUsers(users);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        List<User> users = userRepository.findAllUsers().stream()
                .filter(user -> !user.getId().equals(id))
                .collect(Collectors.toList());
        userRepository.writeUsers(users);
    }

    private void updateUser(User srcUser, User destUser) {
        destUser.setFirstName(srcUser.getFirstName());
        destUser.setLastName(srcUser.getLastName());
        destUser.setLogin(srcUser.getLogin());
        destUser.setPassword(srcUser.getPassword());
    }
}