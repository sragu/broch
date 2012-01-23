package com.sragu.broch;

import com.google.common.io.Closeables;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Bootstraps common scripts to user.home
 */
public class BootStrap {
    public void init() {
        String brochHome = System.getProperty("user.home") + "/.broch";
        System.setProperty("broch.home", brochHome);

        File homeDir = new File(brochHome);
        homeDir.mkdir();
        System.out.println(brochHome);

        exportCommonScripts(homeDir);
    }

    public void exportCommonScripts(File homeDir) {
        try {

            CodeSource codeSource = BootStrap.class.getProtectionDomain().getCodeSource();
            codeSource.getLocation();

            ZipInputStream stream = new ZipInputStream(codeSource.getLocation().openStream());

            ZipEntry nextEntry = null;
            while ((nextEntry = stream.getNextEntry()) != null) {
                String name = nextEntry.getName();
                if (name.startsWith("scripts") && name.endsWith("xml")) {

                        System.out.println(name);
                        URL resource = Resources.getResource(name);
                        File outputFile = new File(homeDir, name);

                            if(outputFile.getParentFile().mkdirs());
                            System.out.println("found a parent, creating: " + outputFile.getParentFile());


                        FileOutputStream output = Files.newOutputStreamSupplier(outputFile).getOutput();
                        Resources.copy(resource, output);
                        Closeables.closeQuietly(output);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
