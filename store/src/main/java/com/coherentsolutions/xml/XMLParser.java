package com.coherentsolutions.xml;

import com.coherentsolutions.sorting.Sorting;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


public class XMLParser {
    private static final String PATH = "store/src/main/resources/config.xml";
    // Parse the XML configuration file and return a map of sorting orders
    public static Map<String, Sorting> getSortInOrder() throws RuntimeException {
        Map<String, Sorting> sortMap = new LinkedHashMap<>();

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(PATH);
            Element root = document.getDocumentElement();
            NodeList children = root.getChildNodes();
            // Iterate through XML elements and extract sorting orders
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child instanceof Element childElement) {
                    // Get field name and sorting order value from XML
                    String key = childElement.getTagName();
                    String value = childElement.getTextContent();
                    Sorting sortingOrder = Sorting.valueOf(value.toUpperCase()); // Convert to enum
                    sortMap.put(key, sortingOrder);
                }
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Parsing error");
        } catch (IOException e) {
            throw new RuntimeException("Error while reading or accessing files, directories and streams");
        } catch (SAXException e) {
            throw new RuntimeException("A SAXException has occurred while reading the " + PATH);
        }

        return sortMap; // Return the map of sorting orders
    }}