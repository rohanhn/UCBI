/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucbi.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author TungVu
 */
public class Controller {

    public static String TAGS = "Controller";

    public static String StartApp(String AppID) {
        String result = "";
        try {
            Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe");
            //p.destroy();
        } catch (Exception ex) {
            System.out.println(TAGS + " - " + ex.getMessage());
        }
        return result;
    }

    public static String StopApp(String AppID) {
        String result = "";
        try {
            Process p = Runtime.getRuntime().exec("taskkill /f /im vlc.exe");
            p.destroy();
        } catch (Exception ex) {
            System.out.println(TAGS + " - " + ex.getMessage());
        }
        return result;
    }
}
