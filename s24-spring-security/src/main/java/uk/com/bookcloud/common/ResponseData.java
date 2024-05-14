package uk.com.bookcloud.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

    private T data;
    private String errorMessage;
    private Integer httpStatusCode;
    private long timestamp;

    public ResponseData(T data, Integer httpStatusCode) {
        this.data = data;
        this.errorMessage = "";
        this.httpStatusCode = httpStatusCode;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseData(String errorMessage, Integer httpStatusCode) {
        this.data = null;
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResponseEntity<ResponseData<T>> success200(T data) {
        return ResponseEntity.ok(new ResponseData<>(data, HttpStatus.OK.value()));
    }

    public static <T> ResponseEntity<ResponseData<T>> success201(T data) {
        return new ResponseEntity<>(new ResponseData<>(data, HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<ResponseData<T>> success202(T data) {
        return new ResponseEntity<>(new ResponseData<>(data, HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
    }

    public static <T> ResponseEntity<ResponseData<T>> success204(T data) {
        return new ResponseEntity<>(new ResponseData<>(data, HttpStatus.NO_CONTENT.value()), HttpStatus.NO_CONTENT);
    }

}
