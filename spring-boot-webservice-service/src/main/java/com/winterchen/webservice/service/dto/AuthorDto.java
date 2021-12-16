package com.winterchen.webservice.service.dto;

import com.winterchen.webservice.service.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author CENTURY
 * @version 1.0
 * @date 2021/12/16 10:12
 * @description TODO
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private String name;

    private List<String> hobby;

    private String birthday;

    private String description;

    private Sex sex;

}