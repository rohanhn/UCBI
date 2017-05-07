/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucbi.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author TungVu
 */
public class Controller {

    public static String TAGS = "Controller";

    public static String StartApp(String AppID) {
        String result = "";
        try {
            String executePath = getDevicePathById(AppID);
//            Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe");
            Process p = Runtime.getRuntime().exec("cmd /c start " + executePath);
            //p.destroy();
        } catch (Exception ex) {
            System.out.println(TAGS + " - " + ex.getMessage());
        }
        return result;
    }

    public static String StartApp2(String AppID) {
        String result = "";
        try {
            String executePath = getDevicePathById(AppID);
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c",
                    executePath);
            Process p = pb.start();            
        } catch (Exception ex) {
            System.out.println(TAGS + " - " + ex.getMessage());
        }
        return result;
    }

    public static String StopApp(String AppID) {
        String result = "";
        try {
            String executeName = getExecuteNameById(AppID);
            Process p = Runtime.getRuntime().exec("taskkill /f /im " + executeName);
            Thread.sleep(1000); // waiting response
            p.destroy();
        } catch (Exception ex) {
            System.out.println(TAGS + " - " + ex.getMessage());
        }
        return result;
    }

    public static String getDevicePathById(String AppID) {
        String executePath = "";
        InputStream is = null;
        String path = null;
        if (Constants.isTestMode) {
            path = Constants.CONFIG_FILE_PATH;
        } else {
            path = "C:\\TungVu\\MyWorkspace\\xampp\\tomcat\\webapps\\telemed\\AppConfig.xml";
        }
        String rootNode = "device";

        try {
            is = new FileInputStream(path);
            DocumentBuilderFactory mDocBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder mDocBuild = mDocBuilderFactory.newDocumentBuilder();
            Document mDoc = mDocBuild.parse(is);
            mDoc.getDocumentElement().normalize();
            NodeList mNodeList = mDoc.getElementsByTagName(rootNode);
            for (int i = 0; i < mNodeList.getLength(); i++) {
                Element e = (Element) mNodeList.item(i);
                if (AppID.equals(e.getElementsByTagName("id").item(0).getTextContent())) {
                    executePath = e.getElementsByTagName("AppPath").item(0).getTextContent();
                }
            }
        } catch (Exception ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return executePath;
    }

    public static String getExecuteNameById(String AppID) {
        String executeName = "";
        InputStream is = null;
        String path = null;
        if (Constants.isTestMode) {
            path = Constants.CONFIG_FILE_PATH;
        } else {
            path = "C:\\TungVu\\MyWorkspace\\xampp\\tomcat\\webapps\\telemed\\AppConfig.xml";
        }
        String rootNode = "device";

        try {
            is = new FileInputStream(path);
            DocumentBuilderFactory mDocBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder mDocBuild = mDocBuilderFactory.newDocumentBuilder();
            Document mDoc = mDocBuild.parse(is);
            mDoc.getDocumentElement().normalize();
            NodeList mNodeList = mDoc.getElementsByTagName(rootNode);
            for (int i = 0; i < mNodeList.getLength(); i++) {
                Element e = (Element) mNodeList.item(i);
                if (AppID.equals(e.getElementsByTagName("id").item(0).getTextContent())) {
                    executeName = e.getElementsByTagName("ExecuteName").item(0).getTextContent();
                }
            }
        } catch (Exception ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return executeName;
    }
}
