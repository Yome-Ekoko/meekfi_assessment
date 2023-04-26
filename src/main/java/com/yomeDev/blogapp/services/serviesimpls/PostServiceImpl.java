package com.yomeDev.blogapp.services.serviesimpls;

import com.yomeDev.blogapp.exceptions.ResourceNotFoundException;
import com.yomeDev.blogapp.models.Category;
import com.yomeDev.blogapp.models.Post;
import com.yomeDev.blogapp.payloads.PostDto;
import com.yomeDev.blogapp.repositories.CategoryRepo;
import com.yomeDev.blogapp.repositories.PostRepo;
import com.yomeDev.blogapp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public String deletePost(Long postId){
        Post post=postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Not Found","Post", "Id",postId.toString()));
        postRepository.delete(post);
        return "Post Deleted Successfully";

    }

    @Override
    public List<PostDto> getALlPosts() {
        List<Post> posts=postRepository.findAll();
        return  posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
    }


    private PostDto mapToDto(Post post){
        PostDto postDto=PostDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .description(post.getDescription())
                .build();
        return postDto;
    }

    private Post mapToEntity(PostDto postRequestDto){
        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .description(postRequestDto.getDescription())
                .build();
        return post;
    }

}
