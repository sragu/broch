package com.sragu.broch;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Main {
    public static void main(String[] args) {
        BrochOptions bean = new BrochOptions();
        CmdLineParser parser = new CmdLineParser(bean);

        try {
            parser.parseArgument(args);

            if (bean.bootstrap) {
                new BootStrap().init(bean.home);
            }

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Usage: ./broch.sh --init");
            parser.printUsage(System.err);
        }
    }

    static class BrochOptions {
        @Option(name = "--init", usage = "sets up the build environment", required = true)
        private boolean bootstrap;
        
        @Option(name = "--home", metaVar = "<path>" , usage = "path to project home, defaults to .build in current dir")
        private String home;
    }
}
