package hu.elte.szakdolgozat.spms.model.rest;

import lombok.Data;

@Data
public class SpmsRestResponse<T> {
    private boolean success;
    private String message;
    private T content;

}
