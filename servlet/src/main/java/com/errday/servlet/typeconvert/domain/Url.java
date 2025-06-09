package com.errday.servlet.typeconvert.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Url {

    private String protocol;
    private String domain;
    private int port;

    @Override
    public String toString() {
        return protocol + "://" + domain + ":" + port;
    }
}
