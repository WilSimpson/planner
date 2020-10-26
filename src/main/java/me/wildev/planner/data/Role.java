package me.wildev.planner.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role
{
    public Role() { }

    public Role(ERole role)
    {
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ERole role;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Role)
        {
            Role role2 = (Role) obj;

            return id.equals(role2.getId()) && role.equals(role2.getRole());
        }

        return false;
    }
}
