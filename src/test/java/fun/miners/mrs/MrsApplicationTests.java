package fun.miners.mrs;

import fun.miners.mrs.model.entity.Category;
import fun.miners.mrs.model.entity.User;
import fun.miners.mrs.repository.FavoritesRepository;
import fun.miners.mrs.repository.MovieRepository;
import fun.miners.mrs.service.FavoritesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MrsApplicationTests {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void contextLoads() {

    }

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

}
