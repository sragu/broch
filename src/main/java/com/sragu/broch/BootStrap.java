package com.sragu.broch;

import com.google.common.io.Resources;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Bootstraps common scripts to user.home
 */
public class BootStrap {
    public void init() {
        String brochHome = System.getProperty("user.home") + "/.broch";
        System.setProperty("broch.home", brochHome);

        new File(brochHome).mkdir();
        System.out.println(brochHome);

        exportCommonScripts(brochHome);
    }

    public void exportCommonScripts(String brochHome) {
        try {
//   /         ZipInputStream stream = new ZipInputStream(new FileInputStream(Resources.getResource("scripts")))


            System.out.println(Resources.getResource("scripts").getPath());

            JarFile file = new JarFile(Resources.getResource("scripts").getPath());

            Enumeration<JarEntry> entries = file.entries();

            while (entries.hasMoreElements()){
                System.out.println(entries.nextElement());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
