package com.hus.hpms.repository.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class RequestDeleteParam
{
    private String id;
    public RequestDeleteParam() {}

    public RequestDeleteParam(String id)
    {
        this.id = id;
    }
}
