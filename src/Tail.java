import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Tail {

    private final Integer charNum;
    private final Integer stringNum;

    public Tail(Integer charNum, Integer stringNum) {
        if (charNum == null && stringNum == null) {
            this.charNum = charNum;
            this.stringNum = 10;
        } else {
            this.charNum = charNum;
            this.stringNum = stringNum;
        }
    }

    private String extractCharacters(InputStream in) throws IOException {
        List<Integer> seq1 = new ArrayList<>();
        List<Integer> seq2 = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        try (InputStreamReader reader = new InputStreamReader(in)) {
            int ch = reader.read();
            while (ch != -1) {
                for (int i = 0; i < charNum; i++) {
                    seq1.add(ch);
                    ch = reader.read();
                    if (ch == -1) {
                        if (!seq2.isEmpty()) {
                            final Integer num = charNum - seq1.size();
                            result.addAll(seq2.subList(seq2.size() - num, charNum));
                        }
                        result.addAll(seq1);
                        break;
                    }
                }
                seq2.clear();
                seq2.addAll(seq1);
                seq1.clear();

            }
        }
        String string = "";
        if (!result.isEmpty()) {
            for (int e : result) {
                string = string + (char) e;
            }
        } else throw new IOException("No input stream found");
        return string.trim();
    }

    private String extractStrings(InputStream in) throws IOException {
        List<String> lines1 = new ArrayList<>();
        List<String> lines2 = new ArrayList<>();
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line = br.readLine();
            while (line != null) {
                for (int i = 0; i < stringNum; i++) {
                    lines1.add(line);
                    line = br.readLine();
                    if (line == null) {
                        if (!lines2.isEmpty()) {
                            final Integer num = stringNum - lines1.size();
                            result.addAll(lines2.subList(lines2.size() - num, stringNum));
                        }
                        result.addAll(lines1);
                        break;
                    }
                }
                lines2.clear();
                lines2.addAll(lines1);
                lines1.clear();

            }
        }
        String string = "";
        if (!result.isEmpty()) {
            for (String e : result) {
                string = string + e + "\n";
            }
        } else throw new IOException("No input stream found");
        return string.trim();
    }

    public String extractTail(InputStream in) throws IOException {
        if (charNum != null) {
            return extractCharacters(in);
        } else {
            return extractStrings(in);
        }
    }

    public String fromFile(String inputFile) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            if (charNum != null) {
                return extractCharacters(inputStream);
            } else {
                return extractStrings(inputStream);
            }
        }
    }

    public static void out(String string, OutputStream out) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
            writer.write(string);
            writer.close();
        }
    }

    public static void toFile(String string, String outputFile) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            out(string, outputStream);
        }
    }
}