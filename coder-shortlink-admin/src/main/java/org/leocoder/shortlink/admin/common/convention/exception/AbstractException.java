package org.leocoder.shortlink.admin.common.convention.exception;

import lombok.Getter;
import org.leocoder.shortlink.admin.common.convention.errorcode.IErrorCode;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author : Leo
 * @version 1.0
 * @date  2025-06-05 21:34
 * @description : 抽象项目中三类异常体系，客户端异常、服务端异常以及远程服务调用异常
 */
@Getter
public abstract class AbstractException extends RuntimeException {

    public final String errorCode;

    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(message) ? message : null).orElse(errorCode.message());
    }
}
