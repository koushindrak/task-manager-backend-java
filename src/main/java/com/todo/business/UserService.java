package com.todo.business;

import com.todo.dao.UserRepository;
import com.todo.dto.UserRequest;
import com.todo.dto.UserResponse;
import com.todo.entity.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService  {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user))
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return new UserResponse(user);
    }

    public User createUser(UserRequest userRequest) {
        User user = userRequest.toUser();
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserRequest user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        BeanUtils.copyProperties(user, existingUser);
        return userRepository.save(existingUser);
    }

    public UserResponse deleteUser(Long id) {
        UserResponse user = getUserById(id);
        userRepository.deleteById(id);
        return user;
    }

}
