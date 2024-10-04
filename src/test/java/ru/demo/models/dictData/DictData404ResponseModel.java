package ru.demo.models.dictData;

import lombok.Data;

import java.util.List;

@Data
public class DictData404ResponseModel {
    private int errorType;
    private String errorCode,
            errorMessage;
    private ErrorData errorData;
    private List<String> errorDetails;

    @Data
    public static class ErrorData {
        private String objectTypeName,
                objectType,
                id;
    }
}
