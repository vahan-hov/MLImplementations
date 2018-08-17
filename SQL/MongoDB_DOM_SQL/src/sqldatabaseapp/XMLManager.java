/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqldatabaseapp;

/**
 *
 * @author vahan
 */
import java.io.File;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLManager {

    String name;
    String username;
    String password;

    public void showXML() {

        try {
            File inputFile = new File("XML_container.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("userdata");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    System.out.println("Name : " + name);
                    username = eElement.getElementsByTagName("username").item(0).getTextContent();
                    System.out.println("Username : " + username);
                    password = eElement.getElementsByTagName("password").item(0).getTextContent();
                    System.out.println("Password : " + password);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insertXML() {
        showXML();
        MongoDBManager mongoDBManager = new MongoDBManager(name,username, password);
//        mongoDBManager.createMongoDB();
//        mongoDBManager.getCollection();
        return mongoDBManager.mainFunc();
    }
}
