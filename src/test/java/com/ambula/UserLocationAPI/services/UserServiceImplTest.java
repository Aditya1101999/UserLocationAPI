package com.ambula.UserLocationAPI.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ambula.UserLocationAPI.model.User;
import com.ambula.UserLocationAPI.repository.UserRepository;
import com.ambula.UserLocationAPI.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private List<User> userList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        user1 = new User("user1", "ROLE_USER");
        user2 = new User("user2", "ROLE_ADMIN");
        userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(user1)).thenReturn(user1);
        User createdUser = userServiceImpl.createUser(user1);
        assertNotNull(createdUser);
        assertEquals(user1.getUsername(), createdUser.getUsername());
    }

    @Test
    public void testGetUserById() {
        Long id = 1L;
        Optional<User> optionalUser = Optional.of(user1);
        when(userRepository.findById(id)).thenReturn(optionalUser);
        User user = userServiceImpl.getUserById(id);
        assertNotNull(user);
        assertEquals(user1.getUsername(), user.getUsername());
    }

    @Test
    public void testUpdateUser() {
        Long id = 1L;
        User updatedUser = new User("updatedUser", "ROLE_USER");
        Optional<User> optionalUser = Optional.of(user1);
        when(userRepository.findById(id)).thenReturn(optionalUser);
        when(userRepository.save(user1)).thenReturn(user1);
        User user = userServiceImpl.updateUser(id, updatedUser);
        assertNotNull(user);
        assertEquals(updatedUser.getUsername(), user.getUsername());
    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;
        Optional<User> optionalUser = Optional.of(user1);
        when(userRepository.findById(id)).thenReturn(optionalUser);
        userServiceImpl.deleteUser(id);
        assertNull(userRepository.findById(id).orElse(null));
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(userList);
        Iterable<User> users = userServiceImpl.getAllUsers();
        assertNotNull(users);
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        assertEquals(userList.size(), 2);
    }
}
