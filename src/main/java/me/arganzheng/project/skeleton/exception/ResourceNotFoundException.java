package me.arganzheng.project.skeleton.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends GuanxingException {

    public ResourceNotFoundException(String msg){
        super(msg);
    }

    private static final long serialVersionUID = 41395180321950486L;

}
