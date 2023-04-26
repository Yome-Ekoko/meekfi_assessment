package com.yomeDev.blogapp.controllers;

import com.yomeDev.blogapp.models.Post;
import com.yomeDev.blogapp.payloads.PostDto;
import com.yomeDev.blogapp.payloads.PostResponse;
import com.yomeDev.blogapp.payloads.PostUpdateRequest;
import com.yomeDev.blogapp.services.PostService;
import com.yomeDev.blogapp.utils.AppConstants;
import jakarta.validation.Valid;
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


    @PostMapping("/create-post/{id}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postRequestDto, @PathVariable(name="id") Long categoryId){
        return new ResponseEntity<>(postService.createPost(postRequestDto,categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());

    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<Post> softDeletePost(@PathVariable("id") Long id ) {
        postService.softDeletePost(id);
        return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") Long postId){
        return  ResponseEntity.ok (postService.deletePost(postId));
    }

    @GetMapping("/{id}/{categoryId}")
    public ResponseEntity<PostDto> viewPost(@PathVariable Long id){
        return ResponseEntity.ok(postService.viewPost(id));

    }

    @PutMapping("/update")
    public ResponseEntity <PostDto> updatePost( @Valid @RequestParam("id") Long postId,
                                                PostUpdateRequest postUpdateRequest) {
        return ResponseEntity.ok( postService.updatePost(postUpdateRequest, postId));

    }
}
