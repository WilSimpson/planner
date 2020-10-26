package me.wildev.planner.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole
{
    @Id
    @Getter
    @Column(name = "user_id")
    private long userID;

    @Getter
    @Setter
    @Column(name = "role_id")
    private long roleID;
}
