package com.digital.lubricentro.servicios;

import org.springframework.stereotype.Service;

import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;

@Service
public class RegCheckService {

    private static final String API_URL = "https://www.regcheck.org.uk/api/reg.asmx";
    private static final String NAMESPACE = "http://www.regcheck.org.uk/";

    public String getVehicleDetails(String registrationNumber, String countryCode, String username) throws Exception {
        // Crear la conexión SOAP
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Crear el mensaje SOAP
        SOAPMessage soapMessage = createSOAPRequest(registrationNumber, countryCode, username);

        // Enviar la solicitud
        SOAPMessage soapResponse = soapConnection.call(soapMessage, API_URL);

        // Procesar la respuesta
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        soapResponse.writeTo(outputStream);

        soapConnection.close();

        return outputStream.toString();
    }

    private SOAPMessage createSOAPRequest(String registrationNumber, String countryCode, String username) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();

        // Estructura del mensaje SOAP
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("reg", NAMESPACE);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("Check", "reg");
        soapBodyElem.addChildElement("RegistrationNumber", "reg").addTextNode(registrationNumber);
        soapBodyElem.addChildElement("CountryCode", "reg").addTextNode(countryCode);
        soapBodyElem.addChildElement("UserName", "reg").addTextNode(username);

        soapMessage.saveChanges();

        return soapMessage;
    }
}
