import com.sun.javafx.scene.control.skin.VirtualFlow;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;


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

    public String extract(InputStream in) throws IOException {
        if (charNum != null) {
            List<Integer> seq1 = new ArrayList<>();
            List<Integer> seq2 = new ArrayList<>();
            List<Integer> result = new ArrayList<>();
            try (InputStreamReader reader = new InputStreamReader(in)) {
                int ch = reader.read();
                if (ch != -1) {
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
                        seq2.clear();
                        seq2.addAll(seq1);
                        seq1.clear();
                    }
                } else throw new IOException(); // !!! + проверить наличие символа \n в конечной строке
            }
            String string = "";
            for (int i : result) {
                string = string + (char) ((int) result.get(i));
            }
        } else {

        }




        // Scanner s = new Scanner(in).useDelimiter("\\A");
        // String result = s.hasNext() ? s.next() : "";
        // return result;
    }

    public String readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            while (br.readLine() != null) {
                sb.append(br.readLine());
                sb.append("\n");
            }
            return sb.toString().trim();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    /* public String extract(String text, String outputFile) {
        if ((charNum != 0) && (stringNum == 0)) {
            return text.substring(text.length() - charNum);
        } else if ((charNum == 0) && (stringNum != 0)) {
            String[] lines = text.split("\n");
        }
        return "";
    }
    */
}