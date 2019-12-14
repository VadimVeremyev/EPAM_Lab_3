package by.bsuir.committee.parser;

import by.bsuir.committee.entity.Enrollee;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDomParser implements XMLParser<Enrollee> {
    private static final String ENROLLEE = "enrollee";
    private static final String FIRST_NAME = "firstName";
    private static final String MIDDLE_NAME = "middleName";
    private static final String LAST_NAME = "lastName";
    private static final String FACULTY_NAME = "facultyName";

    private DocumentBuilder documentBuilder;
    private String sourceFilePath;
    private String xsdFilePath;

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public String getXsdFilePath() {
        return xsdFilePath;
    }

    public  UserDomParser() {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    public  UserDomParser(String sourceFilePath, String xsdFilePath) {
        this();
    	
        this.sourceFilePath = sourceFilePath;
        this.xsdFilePath = xsdFilePath;
    }

    public boolean validate(File xml, File xsd)
    {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(xsd)
                    .newValidator()
                    .validate(new StreamSource(xml));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
    
    @Override
    public List<Enrollee> getData(String path) {
        File sourceFile = new File(sourceFilePath);
        File xsdFile = new File(xsdFilePath);

        List<Enrollee> enrollees = new ArrayList<>();
        Document document;
        
        if (validate(sourceFile, xsdFile)){ 
	        try {
	            document = documentBuilder.parse(sourceFile);
	        } catch (SAXException | IOException e) {
	            return null;
	        }
	
	        Element element = document.getDocumentElement();
	        NodeList nodeEnrollees = element.getElementsByTagName(ENROLLEE);


	        	
	        for (int i = 0; i < nodeEnrollees.getLength(); i++) {
	            if (nodeEnrollees.item(i).getNodeType() == Node.ELEMENT_NODE) {        
	                enrollees.add(getClientElement((Element) nodeEnrollees.item(i)));
	            }
	        }
        }

        return enrollees;
    }

    private Enrollee getClientElement(Element element) {
        Enrollee enrollee;

        try {
            enrollee = new Enrollee();
        } catch (NumberFormatException ex) {
            return null;
        }
        
        enrollee.setFirstName(getElementTextContent(element, FIRST_NAME));
        enrollee.setMiddleName(getElementTextContent(element, MIDDLE_NAME));
        enrollee.setLastName(getElementTextContent(element, LAST_NAME));
        enrollee.setFacultyName(getElementTextContent(element, FACULTY_NAME));
        enrollee.setId(enrollee.hashCode());
        
        return enrollee;
    }

    private static String getElementTextContent(Element element, String elementName) {
        if(element == null)
        	return null;
        
    	NodeList nList = element.getElementsByTagName(elementName);
        if(nList == null)
        	return null;
        
    	Node node = nList.item(0);
    	
    	if(node == null)
    		return null;
    	
        return node.getTextContent();
    }

}