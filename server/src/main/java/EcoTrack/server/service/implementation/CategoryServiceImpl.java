package EcoTrack.server.service.implementation;

import EcoTrack.server.DTO.CategoryDTO;
import EcoTrack.server.entity.Category;
import EcoTrack.server.exception.NotFoundException;
import EcoTrack.server.repository.CategoryRepository;
import EcoTrack.server.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO findDTOById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(CategoryDTO::new)
                .orElseThrow(() -> new NotFoundException("Category not found with : " + id));
    }

    @Override
    public List<CategoryDTO> findAllDTO() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::new).toList();
    }

    @Override
    public void deleteDTOById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO updateDTO(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryDTO.getId())
                .orElseThrow(() -> new NotFoundException("Category not found with : " + categoryDTO.getId()));

        category.setCategoryType(categoryDTO.getCategoryType());
        return new CategoryDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO createDTO(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);
        category.setCategoryType(categoryDTO.getCategoryType());
        return new CategoryDTO(categoryRepository.save(category));
    }
}
