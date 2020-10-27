package me.wildev.planner.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.util.Assert.notNull;

@Entity
@Table(name="users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() { }

    public User(String username, String password, String email, Role role)
    {
        notNull(username, "Method called with null parameter (username)");
        notNull(password, "Method called with null parameter (password)");
        notNull(email, "Method called with null parameter (email)");

        this.username = username;
        this.password = password;
        this.email = email;
        this.roles.add(role);
    }

    public boolean hasRole(Role givenRole)
    {
        for(Role currentRole : roles)
            if(currentRole.equals(givenRole))
                return true;

        return false;
    }

    public boolean addRole(Role role)
    {
        return roles.add(role);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User)
        {
            User user2 = (User) obj;

            return id.equals(user2.getId());
        }

        return false;
    }
}
