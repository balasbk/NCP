package ncp_project;


import java.io.*;
import java.sql.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
public class jdbc{
public static void main(String[] args) { 
try {
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ncp", "root", "1234");
Statement st=con.createStatement();
DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
Document doc = docBuilder.parse (new File("login.xml"));
doc.getDocumentElement().normalize();
System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
NodeList listOfPersons = doc.getElementsByTagName("User");
for(int s=0; s<listOfPersons.getLength(); s++){
Node firstPersonNode = listOfPersons.item(s);
if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){
Element firstPersonElement = (Element)firstPersonNode;
NodeList nameList = firstPersonElement.getElementsByTagName("Id");
Element nameElement =(Element)nameList.item(0);

NodeList textFNList = nameElement.getChildNodes();
String name=((Node)textFNList.item(0)).getNodeValue().trim();

NodeList addressList = firstPersonElement.getElementsByTagName("Name");
Element addressElement =(Element)addressList.item(0);

NodeList textLNList = addressElement.getChildNodes();
String address= ((Node)textLNList.item(0)).getNodeValue().trim();

NodeList addressList1 = firstPersonElement.getElementsByTagName("Password");
Element addressElement1 =(Element)addressList1.item(0);

NodeList textLNList1 = addressElement1.getChildNodes();
String address1= ((Node)textLNList1.item(0)).getNodeValue().trim();

NodeList addressList2 = firstPersonElement.getElementsByTagName("Role");
Element addressElement2 =(Element)addressList2.item(0);

NodeList textLNList2 = addressElement2.getChildNodes();
String address2= ((Node)textLNList2.item(0)).getNodeValue().trim();

int i=st.executeUpdate("INSERT INTO login (id, name, password, role) VALUES ('"+name+"','"+address+"','"+address1+"','"+address2+"')");

}
}
System.out.println("Data is successfully inserted!");
}catch (Exception err) {
System.out.println(" " + err.getMessage ());
}
}
}