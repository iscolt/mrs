package fun.miners.mrs.repository;

import fun.miners.mrs.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
