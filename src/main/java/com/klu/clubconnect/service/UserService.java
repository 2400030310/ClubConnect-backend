package com.klu.clubconnect.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klu.clubconnect.model.User;
import com.klu.clubconnect.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User register(User user) {
        return repo.save(user);
    }

    public User login(String email, String password) {

        System.out.println("EMAIL: [" + email + "]");
        System.out.println("PASSWORD: [" + password + "]");

        User user = repo.findByEmail(email.trim());

        if (user == null) {
            System.out.println("User NOT FOUND");
            return null;
        }

        System.out.println("DB PASSWORD: [" + user.getPassword() + "]");

        if (user.getPassword().trim().equals(password.trim())) {
            System.out.println("LOGIN SUCCESS");
            return user;
        } else {
            System.out.println("PASSWORD MISMATCH");
            return null;
        }
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public boolean deleteUser(Long id) {
        if (!repo.existsById(id)) return false;

        repo.deleteById(id);
        return true;
    }
    public User updateUser(Long id, User updatedUser) {
        User existingUser = repo.findById(id).orElse(null);

        if (existingUser == null) return null;

        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        return repo.save(existingUser);
    }
}