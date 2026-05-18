package com.example.growtogether.config;

import com.example.growtogether.constants.Role;
import com.example.growtogether.entity.Owner;
import com.example.growtogether.entity.Users;
import com.example.growtogether.repository.OwnerRepository;
import com.example.growtogether.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if(!usersRepository.existsByEmailId("anshul.angolkar@coditas.com")) {

            Users userOwner = Users.builder()
                    .fistName("Anshul")
                    .lastName("Angolkar")
                    .emailId("anshul.angolkar@coditas.com")
                    .password(passwordEncoder.encode("anshul@123"))
                    .role(new HashSet<>(Arrays.asList(Role.ADMIN,Role.OWNER,Role.MANAGER, Role.STAFF_WAITER,Role.STAFF_KITCHEN)))
                    .joinedDate(LocalDate.parse("2026-05-18"))
                    .build();

            Users savedOwner = usersRepository.save(userOwner);

            Owner owner = Owner.builder()
                    .user(savedOwner)
                    .build();

            ownerRepository.save(owner);
        }
    }
}
