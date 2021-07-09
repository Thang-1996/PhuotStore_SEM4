package com.example.phuotstore.api;

import com.example.phuotstore.model.*;
import com.example.phuotstore.payload.request.SignupRequest;
import com.example.phuotstore.payload.request.UserRequest;
import com.example.phuotstore.payload.response.MessageResponse;
import com.example.phuotstore.repository.RoleRepository;
import com.example.phuotstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserAPI {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_MANAGER = "ROLE_MANAGER";
    private static final String ROLE_USER = "ROLE_USER";


    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping //read data
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userRepository.getAllUsers(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findUserByID(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalUser.get());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @Valid @RequestBody UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findUserByID(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        User user = new User(userRequest.getUsername(),
            userRequest.getEmail(),
            encoder.encode(userRequest.getPassword()));

        Set<String> strRoles = userRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleName(ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleName(ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "user":
                        Role userRole = roleRepository.findByRoleName(ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        break;
                    default:
                        Role managerRole = roleRepository.findByRoleName(ROLE_MANAGER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(managerRole);
                }
            });
        }
        user.setRoles(roles);
        user.setUserID(optionalUser.get().getUserID());
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
        user.setAvatar(userRequest.getAvatar());
        userRepository.save(user);
        return ResponseEntity.ok(optionalUser.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        Optional<User> optionalUser= userRepository.findUserByID(id);

        if(!optionalUser.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        userRepository.delete(optionalUser.get());
        return ResponseEntity.noContent().build();
    }
}
