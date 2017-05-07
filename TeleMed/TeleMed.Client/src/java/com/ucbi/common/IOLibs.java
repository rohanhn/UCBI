/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucbi.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author TungVu
 */
public class IOLibs {

    public static String TAGS = "IOLibs";

    public static String readFile(String path) {
        String sRet = "";

        try {
            File myFoo = new File(path);
            FileInputStream fis = new FileInputStream(myFoo);
            byte[] data = new byte[(int) myFoo.length()];
            fis.read(data);
            fis.close();
            sRet = new String(data, "UTF-8");
        } catch (IOException ex) {
            System.out.println(TAGS + " - " + ex.getMessage());
        }
        return sRet;
    }

    public static String[] GetPBoxLocalNetwork() {
        String pbox_ip_address = "";
        String pbox_subnet_mask = "";
        String pbox_network = "";
        String pbox_gateway = "";
        String pbox_broadcast = "";
        String[] sRet = new String[5];

        try {
            Process p = Runtime.getRuntime().exec("sudo cat /etc/network/interfaces");
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuffer output = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            p.destroy();
            reader.close();

            String temp = output.toString();
            pbox_ip_address = temp.substring(temp.indexOf("address") + 8, temp.indexOf("network"));
            pbox_network = temp.substring(temp.indexOf("network") + 8, temp.indexOf("netmask"));
            pbox_subnet_mask = temp.substring(temp.indexOf("netmask") + 8, temp.indexOf("broadcast"));
            pbox_broadcast = temp.substring(temp.indexOf("broadcast") + 10, temp.indexOf("gateway"));
            pbox_gateway = temp.substring(temp.indexOf("gateway") + 8, temp.indexOf("allow-hotplug"));
        } catch (Exception ex) {
            System.out.println(TAGS + " - " + ex.getMessage());
        }

        sRet[0] = pbox_ip_address.trim();
        sRet[1] = pbox_subnet_mask.trim();
        sRet[2] = pbox_network.trim();
        sRet[3] = pbox_gateway.trim();
        sRet[4] = pbox_broadcast.trim();

        return sRet;
    }

    public static String writeNetworkConfigFile(String ipadress, String network, String netmask, String broadcast, String gateway) {
        String result = "";
        result = result + "# interfaces(5) file used by ifup(8) and ifdown(8)\n"
                + "\n"
                + "# Please note that this file is written to be used with dhcpcd\n"
                + "# For static IP, consult /etc/dhcpcd.conf and 'man dhcpcd.conf'\n"
                + "\n";
        result = result + "auto lo\n";
        result = result + "iface lo inet loopback\n";
        result = result + "\n";
        result = result + "auto eth0\n";
        result = result + "iface eth0 inet static\n";
        result = result + "\n";
        result = result + "address " + ipadress + "\n";
        result = result + "network " + network + "\n";
        result = result + "netmask " + netmask + "\n";
        result = result + "broadcast " + broadcast + "\n";
        result = result + "gateway " + gateway + "\n";
        result = result + "\n";
        result = result + "allow-hotplug wlan0\n";
        return result;
    }

    public static boolean updateAuthentication(String params) {
        boolean result = false;
//        String[] updateParams = params.split("#");
//        if (updateParams[0].length() > 0 && updateParams[1].length() > 0) {            
//
//            // update authentication for web core
//            InputStream is = null;
//            try {
//                
//                String path = Constants.APACHE_SERVER_AUTHENTICATION_FILE_PATH;
////                String path = "C:\\Users\\TungVu\\Desktop\\tomcat-users.xml";
//                is = new FileInputStream(path);
//                DocumentBuilderFactory mDocBuilderFactory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder mDocBuild = mDocBuilderFactory.newDocumentBuilder();
//                Document mDoc = mDocBuild.parse(is);
//                mDoc.getDocumentElement().normalize();
//                NodeList mNodeList = mDoc.getElementsByTagName("tomcat-users");
//                
//                for (int i = 0; i < mNodeList.getLength(); i++) {
//                    NodeList mNodeListTemp = mNodeList.item(i).getChildNodes();
//                    for (int j = 0; j < mNodeListTemp.getLength(); j++) {                        
//                        Node mNode = mNodeListTemp.item(j);                        
//                        if (mNode.getNodeName().equals("user")) {
//                            NamedNodeMap attr = mNode.getAttributes();                                                       
//                            if (attr.getNamedItem("username").getTextContent().equals(updateParams[0])) {
//                                Node nodeAttr = attr.getNamedItem("username");
//                                nodeAttr.setTextContent(updateParams[0]);
//                                nodeAttr = attr.getNamedItem("password");
//                                nodeAttr.setTextContent(updateParams[1]);
//                            }
//                        }
//                    }
//                }
//                // update
//                result = XMLLibs.updateXmlFile(path, mDoc);
//
//                // close
//                is.close();
//            } catch (Exception e) {
//                result = false;
//            }
//        }

        return result;
    }
}
