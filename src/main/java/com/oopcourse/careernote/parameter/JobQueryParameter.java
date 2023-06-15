package com.oopcourse.careernote.parameter;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobQueryParameter {
    private String attribute;
    private String keyword;
    private String orderBy;
    private String sortRule;
}
