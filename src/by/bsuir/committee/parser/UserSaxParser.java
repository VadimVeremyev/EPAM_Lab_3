package by.bsuir.committee.parser;

import by.bsuir.committee.entity.Enrollee;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import java.util.*;

public class UserSaxParser extends DefaultHandler implements XMLParser<Enrollee> {

    private Enrollee enrollee;
    private String thisElement = "";
    private ArrayList<Enrollee> enrollees = new ArrayList<>();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
        thisElement = qName;

        if (qName.equals("enrollee")) {
            enrollee = new Enrollee();
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        thisElement = "";
        if (qName.equals("enrollee")) {
            enrollees.add(enrollee);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("firstName")) {
        	enrollee.setFirstName(new String(ch, start, length));
        } else if (thisElement.equals("middleName")) {
        	enrollee.setMiddleName(new String(ch, start, length));
        } else if (thisElement.equals("lastName")) {
        	enrollee.setLastName(new String(ch, start, length));
        } else if (thisElement.equals("facultyName")) {
        	enrollee.setFacultyName(new String(ch, start, length));
        } else if (thisElement.equals("id")) {
            enrollee.setId(Integer.parseInt(new String(ch, start, length)));
        } 
    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }

    @Override
    public List<Enrollee> getData(String path) {
        return enrollees;
    }
}
