import java.io.*;
import java.util.List;
import java.util.Locale;

import org.kohsuke.args4j.*;

enum Messages implements Localizable {
    CONFLICTING_OUTPUT_SETTINGS("Conflicting number of options");

    private final String msg;

    Messages(String msg) {
        this.msg = msg;
    }

    @Override
    public String formatWithLocale(Locale locale, Object... args) {
        return String.format(msg, args);
    }

    @Override
    public String format(Object... args) {
        return String.format(msg, args);
    }
}

public class TailLauncher {
    @Option(name = "-c", metaVar = "num", usage = "Sets the number of characters to print")
    private Integer characterNumber = null;

    @Option(name = "-n", metaVar = "num", usage = "Sets the number of strings to print")
    private Integer stringNumber = null;

    @Option(name = "-o", metaVar = "oFile", usage = "Sets the output file")
    private String outputFile = null;

    @Argument(multiValued = true, metaVar = "IFiles", usage = "Input file names")
    private List<String> inputFiles = null;

    public static void main(String[] args) {
        new TailLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if (stringNumber != null && characterNumber != null) {
                throw new CmdLineException(parser, Messages.CONFLICTING_OUTPUT_SETTINGS);
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Tail.jar [-c num | -n num] [-o oFile] iFile1 iFile2 iFile3...");
            new CmdLineParser(new TailLauncher()).printUsage(System.err);
            return;
        }

        Tail tail = new Tail(characterNumber, stringNumber);

        try {
            StringBuilder outputText = new StringBuilder();
            if (inputFiles != null) {
                for (String inputFileName : inputFiles) {
                    outputText = outputText.append("File: ").append(inputFileName).append("\n").append(tail.fromFile(inputFileName)).append("\n\n");
                }
            } else {
                outputText = outputText.append(tail.extractTail(System.in));
            }

            final OutputStream outputStream =
                    (outputFile == null) ? System.out : new FileOutputStream(outputFile);
            final OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(outputText.toString());
            writer.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}