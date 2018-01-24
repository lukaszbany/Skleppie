package skleppie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import skleppie.model.Role;
import skleppie.model.User;
import skleppie.repository.RoleRepository;
import skleppie.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("CUSTOMER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));

        return userRepository.save(user);
    }
}
