
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
    private Boolean outputFile = null;

    @Argument(multiValued = true, metaVar = "IFiles", usage = "Input files name")
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



        /*Recoder recoder = new Recoder(inputEncoding, outputEncoding);
        try {
            int result = recoder.recode(inputFileName, outputFileName);
            System.out.println("Total of " + result + " symbols recoded");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }*/
    }
}
