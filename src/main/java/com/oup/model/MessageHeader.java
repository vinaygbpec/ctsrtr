package com.oup.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name="MessageHeader")
public class MessageHeader {

	 String ID;

	 String CreationDateTime;
}
