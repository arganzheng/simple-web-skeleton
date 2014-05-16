package me.arganzheng.project.skeleton.exception;

public class UserNotLoggedInException extends GuanxingException {

    private static final long serialVersionUID = 3802813786119496861L;

    public UserNotLoggedInException(String msg){
        super(msg);
    }

}
