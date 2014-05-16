package me.arganzheng.project.skeleton.exception;

/**
 * 异常基类
 * 
 * @author arganzheng
 * @date 2013-12-19
 */
public class GuanxingException extends RuntimeException {

    private static final long serialVersionUID = 2518673347989758182L;

    public GuanxingException(String msg){
        super(msg);
    }

    public GuanxingException(String msg, Throwable e){
        super(msg, e);
    }

}
