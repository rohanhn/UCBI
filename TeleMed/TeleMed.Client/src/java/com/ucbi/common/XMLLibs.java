/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucbi.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author TungVu
 */
public class XMLLibs {

    public static NodeList GetXmlNodeList(Document mDoc, String xPathValue) throws XPathExpressionException {
        XPathFactory xPathF = XPathFactory.newInstance();
        XPath myXPath = xPathF.newXPath();
        XPathExpression expr = myXPath.compile(xPathValue);
        NodeList mNodeList = (NodeList) expr.evaluate(mDoc, XPathConstants.NODESET);
        return mNodeList;
    }

    public static String getStringFromDocument(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean writeXMLFile(String path, String sXML) {
        try {
            File myFoo = new File(path);
            FileOutputStream fo = new FileOutputStream(myFoo, false);
            fo.write(sXML.getBytes());
            fo.close();
        } catch (IOException ex) {

        }
        return true;
    }

    public static boolean updateXML(String path, String rootNode, String tagName, String value) {
        boolean result = false;
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            DocumentBuilderFactory mDocBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder mDocBuild = mDocBuilderFactory.newDocumentBuilder();
            Document mDoc = mDocBuild.parse(is);
            mDoc.getDocumentElement().normalize();
            NodeList mNodeList = mDoc.getElementsByTagName(rootNode);
            for (int i = 0; i < mNodeList.getLength(); i++) {
                NodeList mNodeListTemp = mNodeList.item(i).getChildNodes();
                for (int j = 0; j < mNodeListTemp.getLength(); j++) {
                    Node mNode = mNodeListTemp.item(j);
                    if (mNode.getNodeName().equals(tagName)) {
                        mNode.getFirstChild().setNodeValue(value);
                    }
                }
            }
            // update
            result = updateXmlFile(path, mDoc);
        } catch (Exception e) {
            e.printStackTrace();;
        } finally {
            try {
                is.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static boolean updateXmlFile(String path, Document doc) {
        boolean result = false;
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(path);
            transformer.transform(source, streamResult);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
