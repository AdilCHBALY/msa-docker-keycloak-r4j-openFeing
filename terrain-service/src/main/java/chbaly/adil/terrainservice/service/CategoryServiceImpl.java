package chbaly.adil.terrainservice.service;

import chbaly.adil.terrainservice.dto.Request.CategoryRequest;
import chbaly.adil.terrainservice.dto.Response.CategoryResponse;
import chbaly.adil.terrainservice.dto.Taux;
import chbaly.adil.terrainservice.entities.Category;
import chbaly.adil.terrainservice.exception.DataNotFoundException;
import chbaly.adil.terrainservice.external.TauxRestClient;
import chbaly.adil.terrainservice.mapper.CategoryMapper;
import chbaly.adil.terrainservice.repository.CategoryRepository;
import chbaly.adil.terrainservice.util.ExceptionConstant;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final TauxRestClient tauxRestClient;


    public CategoryServiceImpl(CategoryRepository categoryService, TauxRestClient tauxRestClient)  {
        this.categoryRepository = categoryService;
        this.tauxRestClient = tauxRestClient;
    }

    @Override
    public List<CategoryResponse> getAll() {
        //Get All the Categories
        List<Category> categories = categoryRepository.findAll();
        //Use to the Feign Client to Get related Taxes and the Mapper to Handle Our CategoryResponse
        //return the List of Category Response
        return categories.stream().map(category -> CategoryMapper.mapToResponse(category,tauxRestClient.getByCategory(category.getId()))).toList();
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new DataNotFoundException(ExceptionConstant.CATEGORY_NOT_FOUND));
        return CategoryMapper.mapToResponse(category,tauxRestClient.getByCategory(category.getId()));
    }

    @Override
    public void create(CategoryRequest categoryRequest) {
        if(categoryRequest!=null){
            Category category = Category.builder()
                    .createdAt(LocalDateTime.now())
                    .description(categoryRequest.getDescription())
                    .libelle(categoryRequest.getLibelle())
                    .build();
            Category addedCategory = categoryRepository.save(category);
            Taux taux = Taux.builder()
                    .category_id(addedCategory.getId())
                    .value(categoryRequest.getTaux())
                    .createdAt(LocalDateTime.now())
                    .build();
            tauxRestClient.saveTaux(taux);
        }
    }

    @Override
    public void update(CategoryRequest categoryRequest, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new DataNotFoundException(ExceptionConstant.CATEGORY_NOT_FOUND));
        if(categoryRequest!=null){
            category.setDescription(categoryRequest.getDescription());
            category.setLibelle(categoryRequest.getLibelle());
            categoryRepository.save(category);
        }
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new DataNotFoundException(ExceptionConstant.CATEGORY_NOT_FOUND));
        categoryRepository.delete(category);
    }
}
