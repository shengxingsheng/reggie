package com.sxs.exception;

import com.sxs.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author sxs
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * MethodArgumentNotValidException : bean校验异常
     * ConstraintViolationException ：方法参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public R handlerParameterValidationException(Exception e) {
        log.warn("handlerParameterValidationException:{}", e.getMessage());
        if (e instanceof MethodArgumentNotValidException) {
//            List<String> collect = ((MethodArgumentNotValidException) e).getBindingResult()
//                    .getAllErrors()
//                    .stream()
//                    .map(ObjectError -> ObjectError.getDefaultMessage())
//                    .collect(Collectors.toList());
            String message = ((MethodArgumentNotValidException) e).getBindingResult()
                    .getAllErrors()
                    .get(0)
                    .getDefaultMessage();
            return R.error(message);
        }

        if (e instanceof ConstraintViolationException) {
            String message = e.getMessage();
            return R.error(message);
        }
        return R.error("invalid parameter");
    }

    @ExceptionHandler({BusinessException.class})
    public R handlerBusinessException(BusinessException e) {
        log.error("handlerBusinessException："+e.getMessage());
        return R.error(e.getMessage());
    }

    /**
     * 新增用户名重复异常
     * @param e
     * @return
     */
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public R handlerSQLException(Exception e) {
        String message = e.getMessage();
        log.error("handlerSQLException"+message);
        if (message.contains("Duplicate entry")) {
            String[] split = message.substring(message.indexOf("Duplicate entry")).split(" ");
            return R.error(split[2]+"已存在!");
        }
        return R.error("未知错误!");
    }
/*    @ExceptionHandler(Exception.class)
    public R handlerOtherException(Exception e) {
        log.error("handlerOtherException："+e.getMessage());
        return R.error(e.getMessage());
    }*/
}
