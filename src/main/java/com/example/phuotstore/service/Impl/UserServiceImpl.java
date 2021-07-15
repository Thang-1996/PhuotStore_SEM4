package com.example.phuotstore.service.Impl;//package com.example.phuotstore.service.Impl;
//
//import com.example.phuotstore.dto.UserDTO;
//import com.example.phuotstore.model.Role;
//import com.example.phuotstore.model.User;
//import com.example.phuotstore.repository.RoleRepository;
//import com.example.phuotstore.repository.UserRepository;
//import com.example.phuotstore.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public Page<UserDTO> getAllUsers(int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
//        List<UserDTO> userDTOs = new ArrayList<>();
//        Page<User> users = userRepository.getAllUsers(pageable);
//        users.stream().forEach(user -> {
//            UserDTO userDTO = mapEntityToDto(user);
//            userDTOs.add(userDTO);
//        });
//        return (Page<UserDTO>) userDTOs;
//    }
//
//    @Override
//    public UserDTO updateUser(Integer userID, UserDTO userDTO) {
//        User user = userRepository.getById(userID);
//        user.getRoles().clear();
//        mapDtoToEntity(userDTO, user);
//        User userSaved = userRepository.save(user);
//        return mapEntityToDto(userSaved);
//    }
//
//    @Override
//    public String deleteUser(Integer userID) {
//        Optional<User> user = userRepository.findUserByID(userID);
//        //Remove the related courses from student entity.
//        if(user.isPresent()) {
//            user.get().removeRoles();
//            userRepository.deleteById(user.get().getUserID());
//            return "User with id: " + userID + " deleted successfully!";
//        }
//        return null;
//    }
//
//    private void mapDtoToEntity(UserDTO userDTO, User user) {
//        user.setUsername(userDTO.getUsername());
//        user.setEmail(userDTO.getEmail());
//        user.setPhone(userDTO.getPhone());
//        user.setAddress(userDTO.getAddress());
//        if (null == user.getRoles()) {
//            user.setRoles(new HashSet<>());
//        }
//        userDTO.getRoles().stream().forEach(roleName -> {
//            Role role = roleRepository.findByName(roleName);
//            if (null == role) {
//                role = new Role();
//                role.setUsers(new HashSet<>());
//            }
//            role.setRoleName(roleName);
//            user.addRole(role);
//        });
//    }
//    private UserDTO mapEntityToDto(User user) {
//        UserDTO responseDto = new UserDTO();
//        responseDto.setUsername(user.getUsername());
//        responseDto.setUserID(user.getUserID());
//        responseDto.setEmail(user.getEmail());
//        responseDto.setAddress(user.getAddress());
//        responseDto.setPhone(user.getPhone());
//        responseDto.setRoles(user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet()));
//        return responseDto;
//    }
//}
