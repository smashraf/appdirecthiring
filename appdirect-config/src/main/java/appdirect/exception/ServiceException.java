package appdirect.exception;

public class ServiceException extends Exception{
    private static final long serialVersionUID = 1L;
    private int code;
    public ServiceException(String message, Throwable cause,int code) {
        super(message, cause);
        this.code=code;
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServiceException(){
        super();
    }
}
