package chbaly.adil.terrainservice.controller;



import chbaly.adil.terrainservice.dto.Request.CategoryRequest;
import chbaly.adil.terrainservice.dto.Response.CategoryResponse;
import chbaly.adil.terrainservice.service.CategoryServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
//@PreAuthorize("hasAuthority('ADMIN')")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(final CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody CategoryRequest categoryRequest) {
        categoryService.create(categoryRequest);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody CategoryRequest categoryRequest,@PathVariable Long id) {
        categoryService.update(categoryRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
