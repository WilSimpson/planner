package me.wildev.planner.controllers;

import me.wildev.planner.data.User;
import me.wildev.planner.data.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/admin")
public class AdminController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "users")
    public @ResponseBody
    List<User> getUsers(@RequestParam(name = "id", required = false) Long userID)
    {
        if (userID == null)
            return userRepository.findAll();

        return userRepository.findById(userID).isPresent() ?
                List.of(userRepository.findById(userID).get()) : List.of();
    }

    @GetMapping(path = "")
    public @ResponseBody
    String checkHealth()
    {
        return "Health Status: GOOD!";
    }
}
