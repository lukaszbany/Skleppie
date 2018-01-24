package skleppie.service;

import skleppie.model.User;

public interface UserService {
    User findUserByEmail(String email);
    User saveUser(User user);
}
