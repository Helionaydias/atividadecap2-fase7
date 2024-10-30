package model;


import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ErrorMessageModel {
    @Expose
    private String timestamp;
    @Expose
    private int status;
    @Expose
    private String error;
    @Expose
    private String path;
}
