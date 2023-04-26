package com.yomeDev.blogapp.services;

import com.yomeDev.blogapp.payloads.PostDto;
import com.yomeDev.blogapp.payloads.PostResponse;
import com.yomeDev.blogapp.payloads.PostUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postRequestDto, Long categoryId);

    String deletePost(Long postId);

//    List<PostDto> getALlPosts();

    List<PostDto> getAllPosts();

    PostDto viewPost(Long id);

    @Transactional
    PostDto updatePost(PostUpdateRequest postUpdateRequest,Long postId);

    @Transactional
    void softDeletePost(Long id);
}
