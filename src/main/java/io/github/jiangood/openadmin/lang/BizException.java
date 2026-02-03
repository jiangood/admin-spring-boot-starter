package io.github.jiangood.openadmin.lang;

import lombok.Getter;

@Getter
public class BizException extends IllegalStateException {

    private int code;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String prefixMessage, Throwable e) {
        super(prefixMessage + ": " + e.getMessage());
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }
}
