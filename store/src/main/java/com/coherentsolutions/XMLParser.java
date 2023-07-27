package com.coherentsolutions;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class XMLParser {

    public static HashMap<String, String> GetSortInOrder()  {

        try {

            HashMap<String, String> sortMap = new LinkedHashMap<>();
            File xmlFile = new File("C:\\Users\\HalynaSereda\\IdeaProjects\\onlinestore-HalynaSereda\\store\\src\\main\\resources\\config.xml");

            try {
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = documentBuilder.parse(xmlFile);

            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }


            return sortMap;
        } finally {

        }
    }}

