package com.yomeDev.blogapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter

public abstract class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyy-mmm-ddd:mm-ss",timezone = "UTC")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyy-mmm-ddd:mm-ss",timezone = "UTC")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
