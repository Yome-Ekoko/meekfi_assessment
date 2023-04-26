package com.yomeDev.blogapp.repositories;

import com.yomeDev.blogapp.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
