package com.wku.menumoa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T>{
    private String message;
    private T content;

    public ResponseDTO(String message){
        this.message = message;
    }
}
