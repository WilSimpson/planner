package me.wildev.planner.http.responses;

import lombok.Getter;
import lombok.Setter;

public class MessageResponse
{
    @Getter
    @Setter
    public String message;

    public MessageResponse(String message)
    {
        this.message = message;
    }
}
