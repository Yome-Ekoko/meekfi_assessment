package com.yomeDev.blogapp.payloads;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private String description;
    private String category;
}
