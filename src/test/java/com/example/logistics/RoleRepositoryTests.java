package com.example.logistics;

import com.example.logistics.Repository.RoleRepository;
import com.example.logistics.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreatedRoles() {
        Role admin = new Role("Admin");
        Role funders = new Role("Funders");
        Role vendors = new Role("Vendors");
        Role partners = new Role("Partners");

        roleRepository.saveAll(Arrays.asList(admin, funders, vendors, partners));
        long numberOfRoles = roleRepository.count();
        assertEquals(4, numberOfRoles);
    }

    public void testListRoles() {
        List<Role> roleLists = roleRepository.findAll();
        assertThat(roleLists.size()).isGreaterThan(0);

        roleLists.forEach(System.out::println);
    }
}
