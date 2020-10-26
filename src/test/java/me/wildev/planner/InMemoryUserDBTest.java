package me.wildev.planner;

import me.wildev.planner.data.DatabaseLoader;
import me.wildev.planner.data.ERole;
import me.wildev.planner.data.Role;
import me.wildev.planner.data.User;
import me.wildev.planner.data.repos.RoleRepository;
import me.wildev.planner.data.repos.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        PlannerApplication.class,
        H2JpaConfig.class})
@ActiveProfiles("test")
public class InMemoryUserDBTest
{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    void createUser_whenSave_thenGetOk()
    {
        //Make sure the database is initialized with all required data
        loadDatabase();

        //Create new user and add to the database
        User user = new User("bob", "bob", "bob@gmail.com", getRole(ERole.PUBLIC));
        userRepository.save(user);
        long id = user.getId();

        //Get the new number of users
        long initialSize = userRepository.count();

        //Check if the information saved correctly
        User dbUser = userRepository.findById(id).orElseThrow();
        assertNotNull(dbUser);
        assertEquals("bob", dbUser.getUsername());
        assertEquals("bob@gmail.com", dbUser.getEmail());
        assertTrue(dbUser.hasRole(getRole(ERole.PUBLIC)));

        //Check if we can update a user, save and retrieve successfully
        dbUser.addRole(getRole(ERole.ADMIN));
        userRepository.save(dbUser);
        assertEquals(initialSize, userRepository.count());
        dbUser = userRepository.findById(id).orElseThrow();
        assertTrue(dbUser.hasRole(getRole(ERole.ADMIN)));
    }


    private Role getRole(ERole role)
    {
        return roleRepository.findByRole(role).orElseThrow();
    }

    private void loadDatabase()
    {
        DatabaseLoader loader = new DatabaseLoader(userRepository, roleRepository);
        loader.run();
    }
}
