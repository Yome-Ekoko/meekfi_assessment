package com.yomeDev.blogapp.models;

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
    @Column(name= "title", nullable = false)
    private String title;
    @Column(name= "description", nullable = false)
    private String description;
    @Column(name= "content", nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
