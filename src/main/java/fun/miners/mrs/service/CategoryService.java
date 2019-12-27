package fun.miners.mrs.service;

import fun.miners.mrs.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分类业务逻辑层
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

}
