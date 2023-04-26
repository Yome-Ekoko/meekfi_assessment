package com.yomeDev.blogapp.services;

import com.yomeDev.blogapp.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postRequestDto, Long categoryId);

    String deletePost(Long postId);

    List<PostDto> getALlPosts();
}
