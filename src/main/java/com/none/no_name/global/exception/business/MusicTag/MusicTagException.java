package com.none.no_name.global.exception.business.MusicTag;

import com.none.no_name.global.exception.business.BusinessException;
import org.springframework.http.HttpStatus;

public abstract class MusicTagException extends BusinessException {

    protected MusicTagException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
