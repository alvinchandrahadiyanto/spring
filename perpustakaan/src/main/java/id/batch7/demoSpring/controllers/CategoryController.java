package id.batch7.demoSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.batch7.demoSpring.models.dto.request.CategoryRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;
import id.batch7.demoSpring.services.category.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;
  
  private ResponseData responseData;

  @PostMapping
  public ResponseEntity<?> addCategory(@RequestBody @Valid CategoryRequest request) {
    try {
      responseData = categoryService.createCategoryService(request);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      responseData = new ResponseData(500, e.getMessage(), null);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  }

  @GetMapping
  public ResponseEntity<?> getCategories(@RequestParam(value = "status", defaultValue = "") Boolean status) {
    try {
      responseData = categoryService.readCategoriesService(status);
      return ResponseEntity.ok().body(responseData);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      responseData = new ResponseData(500, e.getMessage(), null);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  }

  @GetMapping("/{idCategory}")
  public ResponseEntity<?> getCategoryById(@PathVariable("idCategory") Integer id) throws Exception {
    responseData = categoryService.readCategoryService(id);
    return ResponseEntity.ok().body(responseData);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateCategoryById(@PathVariable Integer id, @RequestBody CategoryRequest request) throws Exception {
    responseData = categoryService.updateCategoryService(id, request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCategory(@PathVariable Integer id) throws Exception {
    responseData = categoryService.deleteCategoryService(id);
    return ResponseEntity.ok().body(responseData);
  }
}
