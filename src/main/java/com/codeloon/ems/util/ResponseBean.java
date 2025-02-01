package com.codeloon.ems.util;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResponseBean {
    String responseCode;
    String responseMsg;
    Object content;
}
