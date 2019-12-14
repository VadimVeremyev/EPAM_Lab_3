package by.bsuir.committee.servlets;

import by.bsuir.committee.parser.UserDomParser;
import by.bsuir.committee.parser.UserSaxParser;
import by.bsuir.committee.parser.UserStAXParser;
import by.bsuir.committee.Constants;
import by.bsuir.committee.entity.Enrollee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int PAGE_SIZE = 10;

    public boolean validate(File xml, File xsd)
    {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(xsd)
                    .newValidator()
                    .validate(new StreamSource(xml));
        } catch (Exception e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }

        return true;
    }
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String xsdPath =  Constants.XSD_FILE;
        String xmlPath =  Constants.XML_FILE;
       
        if (!validate(new File(xmlPath), new File(xsdPath))){
            System.out.println("Invalid format of xmlFile");
            System.exit(0);
        }
 /*
        //Sax PARSER START =====================================
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		
		try {
		    parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) { 
			e.printStackTrace();
		} catch (org.xml.sax.SAXException e) {
			e.printStackTrace();
		}
		
		UserSaxParser saxp = new UserSaxParser();
		
		try {
			parser.parse(new File(xmlPath), saxp);
		} catch (org.xml.sax.SAXException | IOException e) {
			e.printStackTrace();
		}

		List<Enrollee> enrollees = saxp.getData(xmlPath);
		//Sax PARSER END ========================================
*/
        
        
        //************************  DOM parser   *****************/
        UserDomParser dom = new UserDomParser(xmlPath, xsdPath);
        List<Enrollee> enrollees = null;
        enrollees = dom.getData("");
      /********************************************************/

/*
      //*********************StAX************************
        UserStAXParser StAX = null;
        List<Enrollee> enrollees = null;
        try {
            StAX = new UserStAXParser(Files.newInputStream(Paths.get(xmlPath)));
            enrollees = StAX.getData(null);
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
            return;
        }
        finally {
        	if(StAX != null)
        		StAX.close();
        }
*/  
      /********************************************************/
		
        int index = Integer.parseInt(req.getParameter("pageIndex") == null ? "1" : req.getParameter("pageIndex"));

        if (enrollees != null){
            req.setAttribute("enrollees", enrollees.subList((index - 1) * PAGE_SIZE, Math.min(index * PAGE_SIZE, enrollees.size())));
            req.setAttribute("pageSize", PAGE_SIZE);
            int pageCount = enrollees.size() / PAGE_SIZE;
            int mod = (enrollees.size() % PAGE_SIZE) == 0 ? 0 : 1;
            req.setAttribute("pageCount", pageCount + mod);
            req.setAttribute("pageIndex", index);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        try {
			requestDispatcher.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
