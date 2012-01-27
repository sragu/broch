package com.sragu.broch;

import com.google.common.base.Strings;
import com.google.common.io.Closeables;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Bootstraps common scripts to current dir (usually project home)
 */
public class BootStrap {
    Logger logger = LoggerFactory.getLogger(BootStrap.class);

    public void init(String brochHome) {

        if(Strings.isNullOrEmpty(brochHome)){
            brochHome = ".build";
        }

        File homeDir = new File(brochHome);
        homeDir.mkdir();

        logger.debug("Home directory: {}", homeDir.getAbsolutePath());
        exportCommonScripts(homeDir, "scripts/.+");
    }

    public void exportCommonScripts(File homeDir, String pattern) {
        try {

            CodeSource codeSource = BootStrap.class.getProtectionDomain().getCodeSource();
            ZipInputStream stream = new ZipInputStream(codeSource.getLocation().openStream());


            ZipEntry nextEntry = null;
            while ((nextEntry = stream.getNextEntry()) != null) {
                String name = nextEntry.getName();
                if (name.matches(pattern)) {

                    URL resource = Resources.getResource(name);
                    File outputFile = new File(homeDir, name);

                    if (outputFile.getParentFile().mkdirs()) {
                        logger.debug("Found a new parent directory, creating: {}", outputFile.getParentFile());
                    }


                    FileOutputStream output = Files.newOutputStreamSupplier(outputFile).getOutput();
                    Resources.copy(resource, output);
                    Closeables.closeQuietly(output);
                    logger.debug("Copied {}", name);
                }

            }

        } catch (IOException e) {
            logger.error("Bootstrap failure.", e);
        }
    }
}
