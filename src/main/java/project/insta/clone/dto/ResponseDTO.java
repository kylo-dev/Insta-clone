package project.insta.clone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

public class ResponseDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseResultDTO{
        private HttpStatus httpStatus;
        private int data;
    }
}
