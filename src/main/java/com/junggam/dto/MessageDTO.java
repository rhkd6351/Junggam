package com.junggam.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageDTO {

    String message;

    public MessageDTO(String message) {
        this.message = message;
    }
}
