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
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }

    static class BrochOptions {
        @Option(name = "--init", usage = "sets up the build environment")
        private boolean bootstrap;
    }
}
