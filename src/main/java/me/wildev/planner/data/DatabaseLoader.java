package me.wildev.planner.data;


import me.wildev.planner.data.repos.RoleRepository;
import me.wildev.planner.data.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        User wil = new User("wil", "wil", "wil@wildev.me", getRole(ERole.ADMIN));

        if(userRepository.findByUsername("wil").isEmpty())
            this.userRepository.save(wil);
    }


    private Role getRole(ERole role)
    {
        return roleRepository.findByRole(role).orElseThrow();
    }
}
