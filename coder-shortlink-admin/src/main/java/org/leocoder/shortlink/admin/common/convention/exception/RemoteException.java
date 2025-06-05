package org.leocoder.shortlink.admin.common.convention.exception;


import org.leocoder.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import org.leocoder.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-08-23 11:21
 * @description : 远程调用异常
 */
public class RemoteException extends AbstractException {

    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
