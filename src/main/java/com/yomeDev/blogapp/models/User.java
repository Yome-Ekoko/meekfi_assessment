package com.yomeDev.blogapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "_user")
public class User extends BaseClass{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private  String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private  String password;
    @ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
    private Set<Role> roles;
}
