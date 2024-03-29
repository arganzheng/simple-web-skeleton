package me.arganzheng.project.skeleton.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.arganzheng.project.skeleton.exception.RestException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

/**
 * 统一异常处理类，将异常以JSON形式返回给用户。在这里主要是给前端JS用。
 * 
 * @author arganzheng
 * @date 2013-12-07
 */
public class RestHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private final static Logger              log = Logger.getLogger(RestHandlerExceptionResolver.class);

    @Qualifier("jsonConverter")
    private MappingJsonpHttpMessageConverter jsonConverter;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                              Exception ex) {
        // 异常日志统一在这里记录
        log.error(ex.getMessage(), ex);

        try {
            writeJsonResponse(ex, response);
            return new ModelAndView(); // ruturn Empty ModelAndView表示到此结束了。
        } catch (Exception e) {
            log.error("Error rendering json response!", e);
        }

        return null; // 如果自己处理不了，return null让其他的ExceptionResolver处理。
    }

    private void writeJsonResponse(Exception ex, HttpServletResponse response) throws HttpMessageNotWritableException,
                                                                              IOException {
        MediaType jsonMediaType = MediaType.APPLICATION_JSON;

        response.setContentType("application/json;charset=utf-8");

        // if (ex instanceof RestException) {
        // RestException rex = (RestException) ex;
        // jacksonHttpMessageConverter.write(new RestResponse(rex.getErrorCode(), ex.getMessage()), jsonMediaType,
        // new ServletServerHttpResponse(response));
        // } else if (ex instanceof IllegalArgumentException || ex instanceof MissingServletRequestParameterException) {
        // jacksonHttpMessageConverter.write(new RestResponse(RestErrorCode.BIZ_ARGUMENT_INVALID, "请求参数不合法："
        // + ex.getMessage()),
        // jsonMediaType, new ServletServerHttpResponse(response));
        // } else {
        // log.error(ex.getMessage(), ex);
        // jacksonHttpMessageConverter.write(RestResponse.ERROR_UNKNOWN, jsonMediaType,
        // new ServletServerHttpResponse(response));
        // }

        if (ex instanceof RestException) {
            RestException rex = (RestException) ex;
            jsonConverter.write(new RestError(rex.getErrorCode(), ex.getMessage()), jsonMediaType,
                                new ServletServerHttpResponse(response));
        } else if (ex instanceof IllegalArgumentException || ex instanceof MissingServletRequestParameterException) {
            jsonConverter.write(new RestError(RestErrorCode.BIZ_ARGUMENT_INVALID, "Invalid Request Parameter: "
                                                                                  + ex.getMessage()), jsonMediaType,
                                new ServletServerHttpResponse(response));
        } else {
            log.error(ex.getMessage(), ex);
            jsonConverter.write(new RestError(RestErrorCode.UNKONW_ERROR, "System Error!"), jsonMediaType,
                                new ServletServerHttpResponse(response));
        }
    }
}
