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
        List<Character> seq1 = new ArrayList<>();
        List<Character> seq2 = new ArrayList<>();
        try (InputStreamReader reader = new InputStreamReader(in)) {
            int ch = reader.read();
            while (ch != -1) {
                seq2 = seq1;
                seq1 = new ArrayList<>();
                for (int i = 0; i < charNum && ch != -1; i++) {
                    seq1.add((char) ch);
                    ch = reader.read();
                }
            }
        }
        List<Character> result = new ArrayList<>();
        if (!seq2.isEmpty()) {
            final Integer num = charNum - seq1.size();
            result.addAll(seq2.subList(seq2.size() - num, charNum));
        }
        result.addAll(seq1);

        String string = "";
        for (int e : result) {
            string = string + (char) e;
        }
        return string.trim();
    }

    private String extractStrings(InputStream in) throws IOException {
        List<String> lines1 = new ArrayList<>();
        List<String> lines2 = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line = br.readLine();
            while (line != null) {
                lines2 = lines1;
                lines1 = new ArrayList<>();
                for (int i = 0; i < stringNum && line != null; i++) {
                    lines1.add(line);
                    line = br.readLine();
                }
            }
        }
        List<String> result = new ArrayList<>();
        if (!lines2.isEmpty()) {
            final Integer num = stringNum - lines1.size();
            result.addAll(lines2.subList(lines2.size() - num, stringNum));
        }
        result.addAll(lines1);

        String string = "";
        for (String e : result) {
            string = string + e + "\n";
        }
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
            return extractTail(inputStream);
        }
    }
}