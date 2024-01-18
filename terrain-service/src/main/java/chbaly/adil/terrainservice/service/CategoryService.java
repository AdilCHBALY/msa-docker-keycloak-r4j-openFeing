package chbaly.adil.terrainservice.service;

import chbaly.adil.terrainservice.dto.Request.CategoryRequest;
import chbaly.adil.terrainservice.dto.Response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();
    CategoryResponse getById(Long id);
    void create(CategoryRequest  categoryRequest);
    void update(CategoryRequest categoryRequest,Long id);
    void delete(Long id);
}
