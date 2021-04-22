package com.oup.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Getter
@Setter
public class JournalEntry {
	@XmlElement
	String OriginalReferenceDocumentType;
	@XmlElement
	String BusinessTransactionType;
	@XmlElement
	String AccountingDocumentType;
	@XmlElement
	String DocumentHeaderText;
	@XmlElement
	String CreatedByUser;
	@XmlElement
	String CompanyCode;
	@XmlElement
	String DocumentDate;
	@XmlElement
	String PostingDate;
	@XmlElement
	List<Item> Item;
}
