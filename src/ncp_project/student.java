
package ncp_project;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class student {
  public static void main(String args[]) {
    createPrettyXMLUsingDOM();
    
  }
  private static void createPrettyXMLUsingDOM()
  {
      try
      {
        Class.forName("com.mysql.cj.jdbc.Driver");  
          Connection c=DriverManager.getConnection( 
            "jdbc:mysql://localhost:3306/ncp","root","1234");  
          Statement s=c.createStatement();  
          // DATA is populated using US DATA CSV
          ResultSet rs=s.executeQuery("select * from student_details");
          DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();
          // students root element
          Element rootElement = doc.createElement("mentor");
          doc.appendChild(rootElement);
          
          while(rs.next()){  
              
            
        
          
              Element student = doc.createElement("Detail");
              
              Element id = doc.createElement("Id");
              id.setTextContent(String.valueOf(rs.getInt("sid")));
              student.appendChild(id);
              Element name = doc.createElement("Name");
              name.setTextContent(rs.getString("name"));
              student.appendChild(name);
              Element gender = doc.createElement("Gender");
              gender.setTextContent(rs.getString("gender"));
              student.appendChild(gender);
              Element password = doc.createElement("PhoneNumber");
              password.setTextContent(rs.getString("phone number"));
              student.appendChild(password);
              rootElement.appendChild(student);
 
          }
          
          // Write the content into XML file
          DOMSource source = new DOMSource(doc);
          StreamResult result = new StreamResult(new File(".xml"));
          
          TransformerFactory transformerFactory = TransformerFactory.newInstance();
          Transformer transformer = transformerFactory.newTransformer();
          // Beautify the format of the resulted XML
          transformer.setOutputProperty(OutputKeys.INDENT, "yes");
          transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
          transformer.transform(source, result);
      }
      catch(Exception ex)
      {
          ex.printStackTrace();
      }
  }
}