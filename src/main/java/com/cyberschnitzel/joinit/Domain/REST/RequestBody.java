package com.cyberschnitzel.joinit.Domain.REST;

import com.google.gson.annotations.JsonAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestBody {
    @XmlElement String hello;
    @XmlElement String foo;
    @XmlElement Integer count;
}