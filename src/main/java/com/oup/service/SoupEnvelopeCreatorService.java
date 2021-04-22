package com.oup.service;


import com.oup.model.*;
import org.apache.camel.language.bean.Bean;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class SoupEnvelopeCreatorService {

    public String generateSoapEnvelope(JournalEntryBulkCreateRequest journalEntryBulkCreateRequest)throws Exception
    {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage soapMsg = factory.createMessage();
        SOAPPart part = soapMsg.getSOAPPart();
        SOAPEnvelope envelope = part.getEnvelope();
        SOAPHeader header = envelope.getHeader();
        SOAPBody body = envelope.getBody();
        envelope.addNamespaceDeclaration("sfin", "http://sap.com/xi/SAPSCORE/SFIN");



        /*** Creating message header
         * <MessageHeader>
         *   <ID>ad054115</ID>
         *   <CreationDateTime>2021-04-08T15:35:25.1234567Z</CreationDateTime>
         * </MessageHeader>
         * **/
        QName JournalEntryBulkCreateRequestQName = new QName(null, "JournalEntryBulkCreateRequest", "sfin");
        SOAPBodyElement journalEntryBulkCreateRequestSOAPBodyElement = body.addBodyElement(JournalEntryBulkCreateRequestQName);

        QName messageHeaderQName = new QName("MessageHeader");
        SOAPElement messageHeaderSOAPElement = journalEntryBulkCreateRequestSOAPBodyElement.addChildElement(messageHeaderQName);

        QName iddQName = new QName("ID");
        messageHeaderSOAPElement.addChildElement(iddQName).addTextNode(journalEntryBulkCreateRequest.getMessageHeader().getID());

        QName creationDateTimeQName = new QName("CreationDateTime");
        messageHeaderSOAPElement.addChildElement(creationDateTimeQName).addTextNode(journalEntryBulkCreateRequest.getMessageHeader().getCreationDateTime());

        /*** Creating message header
         * <JournalEntryCreateRequest>
         *    <MessageHeader>
         *       <ID>ad054115</ID>
         *       <CreationDateTime>2021-04-08T15:35:25.1234567Z</CreationDateTime>
         *  </MessageHeader>
         *     <JournalEntry>
         *         ...........
         * </JournalEntryCreateRequest>
         * **/
        QName journalEntryCreateRequestQName = new QName("JournalEntryCreateRequest");
        SOAPElement journalEntryCreateRequestSOAPElement = journalEntryBulkCreateRequestSOAPBodyElement.addChildElement(journalEntryCreateRequestQName);

        QName messageHeaderQName2 = new QName("MessageHeader");
        SOAPElement messageHeaderSOAPElement2 = journalEntryCreateRequestSOAPElement.addChildElement(messageHeaderQName2);

        QName iddQName2 = new QName("ID");
        messageHeaderSOAPElement2.addChildElement(iddQName).addTextNode(journalEntryBulkCreateRequest.getJournalEntryCreateRequest().getMessageHeader().getID());

        QName creationDateTimeQName2 = new QName("CreationDateTime");
        messageHeaderSOAPElement2.addChildElement(creationDateTimeQName2).addTextNode(journalEntryBulkCreateRequest.getJournalEntryCreateRequest().getMessageHeader().getCreationDateTime());

        journalEntryCreateRequestSOAPElement.addChildElement(messageHeaderSOAPElement2);

        QName journalEntryQName = new QName("JournalEntry");
        SOAPElement journalEntrySOAPElement = journalEntryCreateRequestSOAPElement.addChildElement(journalEntryQName);

        /*** Creating JournalEntry child elements
         *
         * <JournalEntry>
         *  <OriginalReferenceDocumentType>BKPFF</OriginalReferenceDocumentType>
         *  <BusinessTransactionType>RUFB</BusinessTransactionType>
         *  <AccountingDocumentType>SB</AccountingDocumentType>
         *  <DocumentHeaderText>ADVANTAGE ad054115 280221</DocumentHeaderText>
         *  <CreatedByUser>BATCH</CreatedByUser>
         *  <CompanyCode>UKCM</CompanyCode>
         *  <DocumentDate>2021-02-28</DocumentDate>
         *  <PostingDate>2021-02-28</PostingDate>
         * ..........
         * **/
        QName OriginalReferenceDocumentTypeQName = new QName("OriginalReferenceDocumentType");
        QName BusinessTransactionTypeeQName = new QName("BusinessTransactionType");
        QName AccountingDocumentTypeQName = new QName("AccountingDocumentType");
        QName DocumentHeaderTextQName = new QName("DocumentHeaderText");
        QName CreatedByUserQName = new QName("CreatedByUser");
        QName CompanyCodeeQName = new QName("CompanyCode");
        QName DocumentDateQName = new QName("DocumentDate");
        QName PostingDateQName = new QName("PostingDate");


        journalEntrySOAPElement.addChildElement(OriginalReferenceDocumentTypeQName).addTextNode(journalEntryBulkCreateRequest.getJournalEntryCreateRequest().getJournalEntry().getOriginalReferenceDocumentType());
        journalEntrySOAPElement.addChildElement(BusinessTransactionTypeeQName).addTextNode(journalEntryBulkCreateRequest.getJournalEntryCreateRequest().getJournalEntry().getBusinessTransactionType());
        journalEntrySOAPElement.addChildElement(AccountingDocumentTypeQName).addTextNode(journalEntryBulkCreateRequest.getJournalEntryCreateRequest().getJournalEntry().getAccountingDocumentType());
        journalEntrySOAPElement.addChildElement(DocumentHeaderTextQName).addTextNode(journalEntryBulkCreateRequest.getJournalEntryCreateRequest().getJournalEntry().getDocumentHeaderText());
        journalEntrySOAPElement.addChildElement(CreatedByUserQName).addTextNode(journalEntryBulkCreateRequest.getJournalEntryCreateRequest().getJournalEntry().getCreatedByUser());
        journalEntrySOAPElement.addChildElement(CompanyCodeeQName).addTextNode(journalEntryBulkCreateRequest.getJournalEntryCreateRequest().getJournalEntry().getCompanyCode());
        journalEntrySOAPElement.addChildElement(DocumentDateQName).addTextNode(journalEntryBulkCreateRequest.getJournalEntryCreateRequest().getJournalEntry().getDocumentDate());
        journalEntrySOAPElement.addChildElement(PostingDateQName).addTextNode(journalEntryBulkCreateRequest.getJournalEntryCreateRequest().getJournalEntry().getPostingDate());

        /** Item creation
         * <Item>
         *   <GLAccount>12670000</GLAccount>
         *   <AmountInTransactionCurrency currencyCode="EUR">206.86</AmountInTransactionCurrency>
         *   <AmountInCompanyCodeCurrency currencyCode="GBP">184.72</AmountInCompanyCodeCurrency>
         *   <DebitCreditCode>H</DebitCreditCode>
         *   <DocumentItemText>BALANCING AD054115 ADV I/FACE</DocumentItemText>
         *   <Tax>
         *     <TaxCode>O8</TaxCode>
         *   </Tax>
         *   <AccountAssignment>
         *     <ProfitCenter>UKCMPJN901</ProfitCenter>
         *   </AccountAssignment>
         * </Item>
         */

        // JournalEntryBulkCreateRequest JournalEntryBulkCreateRequest=getJournalEntryBulkCreateRequestFormXML();
        // System.out.println(JournalEntryBulkCreateRequest.getJournalEntryCreateRequest());
        itemCreation(journalEntrySOAPElement, journalEntryBulkCreateRequest.getJournalEntryCreateRequest().getJournalEntry().getItem());

        soapMsg.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");

        // soapMsg.writeTo(System.out);

            /*

            FileOutputStream fOut = new FileOutputStream("SoapMessage.xml");
            soapMsg.writeTo(fOut);
            System.out.println(printSoapMessage(soapMsg));

             */

      //  FileWriter myWriter = new FileWriter(fileName);
       // myWriter.write(printSoapMessage(soapMsg));
       // myWriter.close();
        return printSoapMessage(soapMsg);
    }
    static void itemCreation(SOAPElement journalEntrySOAPElement, List<Item> items) throws Exception {

        items.forEach(item -> {
            try {

                QName itemQName = new QName("Item");
                SOAPElement itemElement = journalEntrySOAPElement.addChildElement(itemQName);

                /***
                 *   <GLAccount>12670000</GLAccount>
                 *   <AmountInTransactionCurrency currencyCode="EUR">206.86</AmountInTransactionCurrency>
                 *    <AmountInCompanyCodeCurrency currencyCode="GBP">184.72</AmountInCompanyCodeCurrency>
                 *    <DebitCreditCode>H</DebitCreditCode>
                 *    <DocumentItemText>BALANCING AD054115 ADV I/FACE</DocumentItemText>
                 *    <Tax>
                 *     <TaxCode>O8</TaxCode>
                 *    </Tax>
                 *    <AccountAssignment>
                 *     <ProfitCenter>UKCMPJN901</ProfitCenter>
                 *    </AccountAssignment>
                 */
                QName GLAccountQName = new QName("GLAccount");
                QName AmountInTransactionCurrencyQName = new QName("AmountInTransactionCurrency");
                QName AmountInCompanyCodeCurrencyQName = new QName("AmountInCompanyCodeCurrency");
                QName DebitCreditCodeQName = new QName("DebitCreditCode");
                QName DocumentItemTextQName = new QName("DocumentItemText");

                itemElement.addChildElement(GLAccountQName).addTextNode(item.getGLAccount());
                SOAPElement AmountInTransactionCurrencyElement = itemElement.addChildElement(AmountInTransactionCurrencyQName).addTextNode(item.getAmountInTransactionCurrency().getText());
                AmountInTransactionCurrencyElement.addAttribute(new QName("currencyCode"), item.getAmountInTransactionCurrency().getCurrencyCode());
                SOAPElement AmountInCompanyCodeCurrencyQNameElement = itemElement.addChildElement(AmountInCompanyCodeCurrencyQName).addTextNode(item.getAmountInCompanyCodeCurrency().getText());
                AmountInCompanyCodeCurrencyQNameElement.addAttribute(new QName("currencyCode"), item.getAmountInCompanyCodeCurrency().getCurrencyCode());

                itemElement.addChildElement(DebitCreditCodeQName).addTextNode(item.getDebitCreditCode());
                itemElement.addChildElement(DocumentItemTextQName).addTextNode(item.getDocumentItemText());

        /*
              <Tax>
         *     <TaxCode>O8</TaxCode>
         *    </Tax>
         */
                QName TaxQName = new QName("Tax");
                SOAPElement taxElement = itemElement.addChildElement(TaxQName);

                QName TaxCodeQName = new QName("TaxCode");
                taxElement.addChildElement(TaxCodeQName).addTextNode(item.getTax().getTaxCode());

                /*
                 *    <AccountAssignment>
                 *     <ProfitCenter>UKCMPJN901</ProfitCenter>
                 *    </AccountAssignment>
                 */
                QName AccountAssignmentQName = new QName("AccountAssignment");
                SOAPElement AccountAssignmentElement = itemElement.addChildElement(AccountAssignmentQName);

                QName ProfitCenterQName = new QName("ProfitCenter");
                AccountAssignmentElement.addChildElement(ProfitCenterQName).addTextNode(item.getAccountAssignment().getProfitCenter());


            } catch (Exception ex) {

            }
        });


    }

    public  String printSoapMessage(final SOAPMessage soapMessage) throws TransformerFactoryConfigurationError,
            TransformerConfigurationException, SOAPException, TransformerException {

        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();

        // Format it
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        final Source soapContent = soapMessage.getSOAPPart().getContent();

        final ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
        final StreamResult result = new StreamResult(streamOut);
        transformer.transform(soapContent, result);

        return streamOut.toString();
    }
    public JournalEntryBulkCreateRequest getJournalEntryBulkCreateRequestObject() {
        JournalEntryBulkCreateRequest journalEntryBulkCreateRequest = new JournalEntryBulkCreateRequest();
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setID("ad054115");
        messageHeader.setCreationDateTime("2021-04-08");
        journalEntryBulkCreateRequest.setMessageHeader(messageHeader);

        JournalEntryCreateRequest journalEntryCreateRequest = new JournalEntryCreateRequest();
        journalEntryCreateRequest.setMessageHeader(messageHeader);
        journalEntryBulkCreateRequest.setJournalEntryCreateRequest(journalEntryCreateRequest);

        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setOriginalReferenceDocumentType("BKPFF");
        journalEntry.setBusinessTransactionType("RUFB");
        journalEntry.setAccountingDocumentType("SB");
        journalEntry.setDocumentHeaderText("ADVANTAGE ad054115 280221");
        journalEntry.setCreatedByUser("BATCH");
        journalEntry.setCompanyCode("UKCM");
        journalEntry.setDocumentDate("2021-02-28");
        journalEntry.setPostingDate("2021-02-28");

        journalEntryCreateRequest.setJournalEntry(journalEntry);

        Item item1 = new Item();
        item1.setGLAccount("12670000");
        item1.setDebitCreditCode("H");
        item1.setDocumentItemText("BALANCING AD054115 ADV I/FACE");
        Tax tax = new Tax();
        tax.setTaxCode("O8");
        item1.setTax(tax);
        AccountAssignment accountAssignment = new AccountAssignment();
        accountAssignment.setProfitCenter("UKCMPJN901");
        item1.setAccountAssignment(accountAssignment);

        AmountInTransactionCurrency amountInTransactionCurrency = new AmountInTransactionCurrency();
        amountInTransactionCurrency.setCurrencyCode("EUR");
        amountInTransactionCurrency.setText("206.86");

        AmountInCompanyCodeCurrency amountInCompanyCodeCurrency = new AmountInCompanyCodeCurrency();
        amountInCompanyCodeCurrency.setCurrencyCode("GBP");
        amountInCompanyCodeCurrency.setText("184.72");

        item1.setAmountInCompanyCodeCurrency(amountInCompanyCodeCurrency);
        item1.setAmountInTransactionCurrency(amountInTransactionCurrency);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item1);
        items.add(item1);
        journalEntry.setItem(items);
        return journalEntryBulkCreateRequest;

    }

}
