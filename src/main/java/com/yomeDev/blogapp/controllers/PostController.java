package com.yomeDev.blogapp.controllers;

import com.yomeDev.blogapp.payloads.PostDto;
import com.yomeDev.blogapp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    final PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-post/{id}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postRequestDto, @PathVariable(name="id") Long categoryId){
        return new ResponseEntity<>(postService.createPost(postRequestDto,categoryId), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") Long postId){
        return  ResponseEntity.ok (postService.deletePost(postId));
    }

    @GetMapping("/all-posts")
    public List<PostDto> getAllPost(){
        return postService.getALlPosts();
    }
}
