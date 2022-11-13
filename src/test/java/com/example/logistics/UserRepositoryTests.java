package com.example.logistics;

import com.example.logistics.Repository.RoleRepository;
import com.example.logistics.Repository.UserRepository;
import com.example.logistics.model.Role;
import com.example.logistics.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String prevPassword = "password";
        String encodedPassword = passwordEncoder.encode(prevPassword);
        User newUser = new User(1, "John", "John@admin.com", encodedPassword, null);

        User savedUser = userRepository.save(newUser);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testAssignRoleToUsers() {
        Integer userId = 1;
        Integer roleId = 1;

        User user = userRepository.findById(userId).get();
        System.out.println("userid :" + user);
        Role role = roleRepository.findById(roleId).get();

        user.addRole(new Role(roleId));

        User updatedUser = userRepository.save(user);

        assertThat(updatedUser.getRoles()).isNotNull();
    }
}
