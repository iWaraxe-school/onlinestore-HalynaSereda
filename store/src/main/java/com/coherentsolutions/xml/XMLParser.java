package com.coherentsolutions.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class XMLParser {

    public static LinkedHashMap<String, String> GetSortInOrder() throws RuntimeException {

        LinkedHashMap<String, String> sortMap = new LinkedHashMap<>();
        File xmlFile = new File("C:\\Users\\HalynaSereda\\IdeaProjects\\onlinestore-HalynaSereda\\store\\src\\main\\resources");

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            Element root=document.getDocumentElement();
            NodeList children=root.getChildNodes();
            for(int i=0;i<children.getLength();i++)
            {
                Node child=children.item(i);
                if (child instanceof Element)
                {
                    Element childElement=(Element) child;
                    Text textNode=(Text)childElement.getFirstChild();
                    String text =textNode.getData().trim();
                    System.out.println(text);

                }


        }} catch (ParserConfigurationException e) {
            throw new RuntimeException("Parsing error");
        } catch (IOException e) {
            throw new RuntimeException("File" + xmlFile  + "not found");
        } catch (SAXException e) {
            throw new RuntimeException( "A SAXException has occurred while reading the " + xmlFile );
        }
        return sortMap;
    }}