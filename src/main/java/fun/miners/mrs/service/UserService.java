package fun.miners.mrs.service;

import fun.miners.mrs.model.entity.User;
import fun.miners.mrs.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户业务逻辑层
 */
@Service
public class UserService {

    private UserRepository userRepository;

    /**
     * 登陆
     *
     * @param user
     * @return
     */
    public User login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
    }
}
