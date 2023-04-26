package com.yomeDev.blogapp.payloads;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PostUpdateRequest {
    private Long postId;
    private PostDto postDto;
    private Long categoryId;
}
