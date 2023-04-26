package com.yomeDev.blogapp.models;

import com.yomeDev.blogapp.enums.GenericStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="post", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post extends BaseClass{
    private String title;
    private String content;
    private String imageUrl;

    @Enumerated
    private GenericStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
