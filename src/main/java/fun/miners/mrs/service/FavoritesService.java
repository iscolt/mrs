package fun.miners.mrs.service;

import fun.miners.mrs.model.entity.Favorites;
import fun.miners.mrs.model.entity.User;
import fun.miners.mrs.repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    /**
     * 查用户收藏夹
     *
     * @param user
     * @return
     */
    public List<Favorites> findByUser(User user) {
        return favoritesRepository.findAllByUser(user);
    }

}
