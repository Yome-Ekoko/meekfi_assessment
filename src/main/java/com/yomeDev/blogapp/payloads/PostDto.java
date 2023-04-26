package com.yomeDev.blogapp.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
//import jakarta.validation.constraints.NotEmpty;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    @NotEmpty
    @Size(min = 2,message = "post title should have at least two characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "post Content should have at least 10 characters")
    private String content;
    @NotEmpty
    private String imageUrl;
    private String category;
}
