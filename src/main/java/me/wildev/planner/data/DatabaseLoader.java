package me.wildev.planner.data;


import me.wildev.planner.data.repos.RoleRepository;
import me.wildev.planner.data.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner
{
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRepository, RoleRepository roleRepository)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... strings)
    {
        for(ERole role : ERole.values())
        {
            if(roleRepository.findByRole(role).isEmpty())
            {
                roleRepository.save(new Role(role));
            }
        }

        List<User> defaultUsers = List.of(
            new User("wil", "wil", "wil@wildev.me", getRole(ERole.ADMIN)),
            new User("bob", "bob", "bob@wildev.me", getRole(ERole.USER)),
            new User("tom", "tom", "tom@wildev.me", getRole(ERole.USER)),
            new User("tim", "tim", "tim@wildev.me", getRole(ERole.ADMIN)),
            new User("sam", "sam", "sam@wildev.me", getRole(ERole.USER))
        );

        for(User defaultUser : defaultUsers)
        {
            if(userRepository.findByUsername(defaultUser.getUsername()).isEmpty())
                userRepository.save(defaultUser);
        }
    }


    private Role getRole(ERole role)
    {
        return roleRepository.findByRole(role).orElseThrow();
    }
}
