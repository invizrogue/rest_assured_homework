package study.qa.model;

import lombok.Data;

@Data
public class RegisterResponseModel {

    Integer id;
    String token;
    String error;
}
