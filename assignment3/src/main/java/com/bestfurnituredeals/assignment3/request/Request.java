package com.bestfurnituredeals.assignment3.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Request {
    private String requestName;
    private Object requestObject;
    private RequestType requestType;
}
