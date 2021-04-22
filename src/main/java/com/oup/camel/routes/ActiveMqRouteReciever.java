package com.oup.camel.routes;

import com.oup.camel.processor.DATFileProcessor;
import com.oup.model.JournalEntryBulkCreateRequest;
import com.oup.service.SoupEnvelopeCreatorService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqRouteReciever extends RouteBuilder {
	@Autowired
	private DATFileProcessor datFileProcessor;
	@Override
	public void configure() throws Exception {
		from("file://source?delay=1000").process(datFileProcessor).to("file://destination?fileName=soap.xml");

	}

}
