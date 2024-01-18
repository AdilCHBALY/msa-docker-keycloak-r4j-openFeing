package chbaly.adil.terrainservice.exception;

import lombok.Data;

@Data
public class ExceptionResponse {
    private String message;
    private String callerUrl;

}
