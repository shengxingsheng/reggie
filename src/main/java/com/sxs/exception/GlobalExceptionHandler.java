package com.sxs.exception;

import com.sxs.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

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
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public R handlerParameterValidationException(Exception e) {
        log.warn("Exception:{}", e.getMessage());
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
        return R.error(e.getMessage());
    }
    @ExceptionHandler()
    public R handlerOrderException(Exception e) {
        return R.error(e.getMessage());
    }
}
