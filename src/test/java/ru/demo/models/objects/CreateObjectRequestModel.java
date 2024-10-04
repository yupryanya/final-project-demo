package ru.demo.models.objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateObjectRequestModel {
    private String code,
            address,
            name,
            objType,
            comment,
            parentCode;
}
