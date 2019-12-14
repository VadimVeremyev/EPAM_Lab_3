package by.bsuir.committee.parser;

import by.bsuir.committee.entity.Enrollee;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import java.io.InputStream;
import java.util.ArrayList;

public class UserStAXParser implements AutoCloseable, XMLParser<Enrollee> {
    
	private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    private final XMLStreamReader reader;

    private ArrayList<Enrollee> enrollees = new ArrayList<>();

    @Override
    public ArrayList<Enrollee> getData(String path) {

        try {
            while (reader.hasNext()) { 
                int event = reader.next();   
                if (event == XMLEvent.START_ELEMENT &&
                        "enrollee".equals(reader.getLocalName())) {
                    Enrollee enrollee = new Enrollee();

                    doUntil(XMLEvent.START_ELEMENT, "firstName");
                    enrollee.setFirstName(reader.getElementText());
                    
                    doUntil(XMLEvent.START_ELEMENT, "middleName");
                    enrollee.setMiddleName(reader.getElementText());
                    
                    doUntil(XMLEvent.START_ELEMENT, "lastName");
                    enrollee.setLastName(reader.getElementText());
                    
                    doUntil(XMLEvent.START_ELEMENT, "facultyName");
                    enrollee.setFacultyName(reader.getElementText());
                  
                    doUntil(XMLEvent.START_ELEMENT, "id");
                    enrollee.setId(Integer.parseInt(reader.getElementText()));
                    
                    enrollees.add(enrollee);
                }
                event = XMLEvent.START_ELEMENT;
            }
        } catch (XMLStreamException e) {
            System.out.println("SrAX parse error: " + e.getMessage());
        }
       
        return enrollees;
    }

    public UserStAXParser(InputStream is) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(is);
    }


    public XMLStreamReader getReader() {
        return reader;
    }

    private boolean doUntil(int stopEvent, String value) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == stopEvent && value.equals(reader.getLocalName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) { // empty
            }
        }
    }
}