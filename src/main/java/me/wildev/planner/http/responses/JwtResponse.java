package me.wildev.planner.http.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JwtResponse
{
    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String type = "Bearer";

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
