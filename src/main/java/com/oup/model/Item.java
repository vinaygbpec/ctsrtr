package com.oup.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter

public class Item {

	private String GLAccount;
	@XmlElement
	private AmountInTransactionCurrency AmountInTransactionCurrency;
	@XmlElement
	private AmountInCompanyCodeCurrency AmountInCompanyCodeCurrency;

	private String DebitCreditCode;

	private String DocumentItemText;
	@XmlElement
	private Tax Tax;
	@XmlElement
	private AccountAssignment AccountAssignment;
}
