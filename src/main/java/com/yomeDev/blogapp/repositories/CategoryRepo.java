package com.yomeDev.blogapp.repositories;

import com.yomeDev.blogapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long > {
}
