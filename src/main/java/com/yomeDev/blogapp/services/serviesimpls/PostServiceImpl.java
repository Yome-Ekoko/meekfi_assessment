package com.yomeDev.blogapp.services.serviesimpls;

import com.yomeDev.blogapp.enums.GenericStatus;
import com.yomeDev.blogapp.exceptions.ResourceNotFoundException;
import com.yomeDev.blogapp.models.Category;
import com.yomeDev.blogapp.models.Post;
import com.yomeDev.blogapp.payloads.PostDto;
import com.yomeDev.blogapp.payloads.PostUpdateRequest;
import com.yomeDev.blogapp.repositories.CategoryRepo;
import com.yomeDev.blogapp.repositories.PostRepo;
import com.yomeDev.blogapp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @Autowired
    private CategoryRepo categoryRepository;
    @Autowired
    private PostRepo postRepository;
    @Override
    public PostDto createPost(PostDto postRequestDto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Not Found","Category", "Id",categoryId.toString()));

        Post post = mapToEntity(postRequestDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);

        PostDto postResponse = mapToDto(newPost);
        postResponse.setCategory(category.getName());
        return postResponse;
    }


    @Override
    public List<PostDto> getAllPosts(){
        List<Post> posts=postRepository.findAll();
        List<PostDto> postDtos=new ArrayList<>();
        for(Post post:posts){
         PostDto postDto=new PostDto();
         postDto.setTitle(post.getTitle());
         postDto.setCategory(post.getCategory().getName());
         postDto.setImageUrl(post.getImageUrl());
         postDto.setContent(post.getContent());
         postDtos.add(postDto);

        }
        return postDtos;



    }

    private PostDto mapToDto(Post post){
        PostDto postDto=PostDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .build();
        return postDto;
    }

    private Post mapToEntity(PostDto postRequestDto){
        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .imageUrl(postRequestDto.getImageUrl())
                .build();
        return post;
    }
    @Override
    public PostDto viewPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        if(post.getStatus().equals(GenericStatus.DELETED))
            throw new RuntimeException("Not Found");
        return responseMapper(post);
    }
    protected PostDto responseMapper(Post post){
        return PostDto.builder()
                .title(post.getTitle())
                .category(post.getCategory().getName())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .build();
    }

    @Override
    public PostDto updatePost(PostUpdateRequest postUpdateRequest, Long postId) {
        Category category = findCategory(postUpdateRequest.getCategoryId());
        Post post = findPost(postId);

        if(post.getStatus().equals(GenericStatus.DELETED)) {
            throw new RuntimeException("Not Found");
        }

        post.setTitle(postUpdateRequest.getPostDto().getTitle());
        post.setImageUrl(postUpdateRequest.getPostDto().getImageUrl());
        post.setContent(postUpdateRequest.getPostDto().getContent());
        post.setUpdatedAt(new Date());
        post.setCategory(category);
        postRepository.save(post);
        return responseMapper(post);
    }

    @Override
    public void softDeletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        post.setStatus(GenericStatus.DELETED);
        postRepository.save(post);


    }
    @Override
    public String deletePost(Long postId){
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Not Found","Post", "Id",postId.toString()));
        postRepository.delete(post);
        return "Post Deleted Successfully";

    }
    protected Post findPost(Long postId){
        return postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Not Found","Post","Id",postId.toString()));
    }

    protected Category findCategory(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(()
                -> new ResourceNotFoundException("Not found","Category","Id",categoryId.toString()));
    }

}
