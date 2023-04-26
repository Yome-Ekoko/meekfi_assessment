package com.yomeDev.blogapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "category",uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Category extends BaseClass{
    @Column(name = "name",nullable = false)
    private String name;

}