package fun.miners.mrs;

import fun.miners.mrs.model.entity.Category;
import fun.miners.mrs.model.entity.User;
import fun.miners.mrs.repository.FavoritesRepository;
import fun.miners.mrs.repository.MovieRepository;
import fun.miners.mrs.repository.UserRepository;
import fun.miners.mrs.service.FavoritesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MrsApplicationTests {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private FavoritesService favoritesService;

    @Test
    public void TestFavorites() {
        User user = new User();
        user.setId(1);
        System.out.println(favoritesService.findByUser(user));
    }

    @Test
    public void TestMovies() {
        Category category = new Category();
        category.setId(1);
        System.out.println(movieRepository.findAllByCategory(category));
    }

    @Test
    public void TestsUser() {
        System.out.println(userRepository.findByUsernameAndPassword("admin", "e10adc3949ba59abbe56e057f20f883e"));
    }
}
