package com.oup.camel.processor;

import com.oup.model.JournalEntryBulkCreateRequest;
import com.oup.service.SoupEnvelopeCreatorService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DATFileProcessor implements Processor {
    @Autowired
    private SoupEnvelopeCreatorService soupEnvelopeCreatorService;

    @Override
    public void process(Exchange exchange) throws Exception {
        String body=exchange.getIn().getBody(String.class);
        System.out.println(body);
        JournalEntryBulkCreateRequest journalEntryBulkCreateRequest=soupEnvelopeCreatorService.getJournalEntryBulkCreateRequestObject();
        exchange.getIn().setBody(soupEnvelopeCreatorService.generateSoapEnvelope(journalEntryBulkCreateRequest));
    }
}
