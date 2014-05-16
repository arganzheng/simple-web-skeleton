package me.arganzheng.project.skeleton.exception;

import me.arganzheng.project.skeleton.common.RestErrorCode;

public class UnknownResourceException extends RestException {

    private static final long serialVersionUID = -3163077053140122195L;

    public UnknownResourceException(String msg){
        super(RestErrorCode.RESOURCE_NOT_FOUND, msg);
    }
}
