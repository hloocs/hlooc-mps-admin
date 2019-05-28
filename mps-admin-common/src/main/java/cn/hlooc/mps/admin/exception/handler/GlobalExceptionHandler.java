package cn.hlooc.mps.admin.exception.handler;

import cn.hlooc.mps.admin.exception.EntityExistException;
import cn.hlooc.mps.admin.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import cn.hlooc.mps.admin.exception.BadRequestException;
import cn.hlooc.mps.admin.exception.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

/**
 * @author hlooc
 * @date 2019-05-18 17:34:51
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_EXCEPTION = "0000EE";

    /**
     * 处理所有不可知的异常
     *
     * @param e 不可知异常信息
     * @return 响应对象
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));

        return buildResponseEntity(BAD_REQUEST.value(), ERROR_EXCEPTION, e.getLocalizedMessage());
    }

    /**
     * 处理 接口无权访问异常AccessDeniedException
     *
     * @param e 接口无权访问异常
     * @return 响应对象
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(FORBIDDEN.value(), ERROR_EXCEPTION, e.getLocalizedMessage());
    }

    /**
     * 处理自定义异常
     *
     * @param e 处理自定义异常
     * @return 响应对象
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(e.getStatus(), ERROR_EXCEPTION, e.getLocalizedMessage());
    }

    /**
     * 处理 EntityExist
     *
     * @param e 对象已存在异常
     * @return 响应对象
     */
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ApiError> entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(BAD_REQUEST.value(), ERROR_EXCEPTION, e.getLocalizedMessage());
    }

    /**
     * 处理 EntityNotFound
     *
     * @param e 对象不存在异常
     * @return 响应对象
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(NOT_FOUND.value(), ERROR_EXCEPTION, e.getLocalizedMessage());
    }

    /**
     * 处理所有接口数据验证异常
     *
     * @param e 数据验证异常
     * @return 响应对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
        return buildResponseEntity(BAD_REQUEST.value(), ERROR_EXCEPTION, errorMessage);
    }

    /**
     * 统一返回
     *
     * @param status  响应状态
     * @param code    响应代码
     * @param message 响应明细
     * @return 响应信息
     */
    private ResponseEntity<ApiError> buildResponseEntity(int status, String code, String message) {

        ApiError apiError = ApiError.builder()
                .code(code)
                .status(status)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiError);
    }
}
