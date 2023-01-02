package id.batch7.demoSpring.services.category;

import id.batch7.demoSpring.models.dto.request.CategoryRequest;
import id.batch7.demoSpring.models.dto.response.ResponseData;

public interface CategoryService {
  // kerangka methods untuk CRUD Category
  // Create
  ResponseData createCategoryService(CategoryRequest request) throws Exception;

  // Read
  ResponseData readCategorysService(Boolean status);

  // By Id
  ResponseData readCategoryService(Integer id) throws Exception;

  // Update
  ResponseData updateCategoryService(Integer id, CategoryRequest request) throws Exception;

  // Delete
  ResponseData deleteCategoryService(Integer id) throws Exception;
}
