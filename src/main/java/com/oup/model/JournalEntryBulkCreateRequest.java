package com.oup.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name="JournalEntryBulkCreateRequest")

public class JournalEntryBulkCreateRequest {

	@XmlElement
	private MessageHeader MessageHeader;
	@XmlElement
	private JournalEntryCreateRequest JournalEntryCreateRequest;
}
