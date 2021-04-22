package com.oup.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
public class AccountAssignment {
	@XmlElement
	private String ProfitCenter;
}
