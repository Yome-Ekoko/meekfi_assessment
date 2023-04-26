package com.yomeDev.blogapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment",uniqueConstraints = {@UniqueConstraint(columnNames = {"content"})})
public class Comment extends BaseClass{
    @Column(name = "content",nullable = false)
    private String content;
//    @OneToMany
//    private Post post;
}