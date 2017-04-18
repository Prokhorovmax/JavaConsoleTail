import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;


public class TailTest {

    // Texts
    private final String text1 = "";
    private final String text2 = "Hello world!";
    private final String text3 = "Для написания разных видов программ сейчас\n" +
            "применяются разные языки программирования.\n" +
            "Например, в сфере мобильных программ сейчас правят\n" +
            "бал языки Swift (мобильные устройства под\n" +
            "управлением iOS) и Java (устройства под\n" +
            "управлением Android). Системные программы, как\n" +
            "правило, пишутся на языках C или {cpp}. Эти же\n" +
            "языки долгое время использовались и для создания\n" +
            "встраиваемых программ, но в последние годы в этой\n" +
            "области набирает популярность язык Java. Для\n" +
            "написания web-клиентов часто используется\n" +
            "JavaScript, а в простых случаях -- язык разметки\n" +
            "страниц HTML. Web-серверы используют опять-таки\n" +
            "Java (в сложных случаях), а также Python и PHP (в\n" +
            "более простых). Наконец, простые desktop-программы\n" +
            "сейчас могут быть написаны на самых разных языках,\n" +
            "и выбор во многом зависит от сложности программы,\n" +
            "области её использования, предполагаемой\n" +
            "операционной системы. В первую очередь следует\n" +
            "назвать языки Java, {cpp}, C#, Python, Visual\n" +
            "Basic, Ruby, Swift.\n" +
            "Самым универсальным и одновременно самым\n" +
            "распространённым языком программирования на данный\n" +
            "момент следует считать язык Java. Java в широком\n" +
            "смысле -- не только язык, но и платформа для\n" +
            "выполнения программ под самыми разными\n" +
            "операционными системами и на разной аппаратуре.\n" +
            "Такая универсальность обеспечивается наличием\n" +
            "виртуальной машины Java -- системной программы,\n" +
            "интерпретирующей Java байт-код в машинные коды\n" +
            "конкретного компьютера или системы. Java также\n" +
            "включает богатейший набор библиотек для\n" +
            "разработки.";

    // Expected test results
    private final String result1 = "момент следует считать язык Java. Java в широком\n" +
            "смысле -- не только язык, но и платформа для\n" +
            "выполнения программ под самыми разными\n" +
            "операционными системами и на разной аппаратуре.\n" +
            "Такая универсальность обеспечивается наличием\n" +
            "виртуальной машины Java -- системной программы,\n" +
            "интерпретирующей Java байт-код в машинные коды\n" +
            "конкретного компьютера или системы. Java также\n" +
            "включает богатейший набор библиотек для\n" +
            "разработки.";
    private final String result2 = "конкретного компьютера или системы. Java также\r\n" +
            "включает богатейший набор библиотек для\r\n" +
            "разработки.";
    private final String result3 = "виртуальной машины Java -- системной программы,\n" +
            "интерпретирующей Java байт-код в машинные коды\n" +
            "конкретного компьютера или системы. Java также\n" +
            "включает богатейший набор библиотек для\n" +
            "разработки.";
    private final String result4 = "Electronic circuits work a thousand times more rapidly than nerve cells in the human brain.\n" +
            "A problem that takes the human brain 2 years in order to solve it can be solved by a computer in one minute.\n" +
            "The heart of the electronic computer is its transistors.\n" +
            "In order to work a computer must have instructions; this is called “programming”.\n" +
            "There are two main types of electronic computers: analogue and digital.\n" +
            "In analogue computers problems are solved by analogy, the problems which analogue computers can solve are the following:\n" +
            "mechanical forces, speeds, rotations, etc.\n" +
            "Analogue computers are used for investigation of mechanical processes, in general,\n" +
            "they are used for scientific and engineering problems in which great accuracy is not required\n" +
            "but answers accurate enough are required quickly.\n" +
            "In digital computers problems are solved by counting. They may be very large and powerful.\n" +
            "All the data connected with the problem which must be solved are converted into electrical pulses\n" +
            "by very fast electronic switches and these pulses are stored and counted.\n" +
            "With modern electronic devices a single switching operation can take place\n" +
            "in a few nanoseconds (a nanosecond is a thousand – millionth of a second).";
    private final String result5 = "Для написания разных видов программ сейчас\n" +
            "применяются разные языки программирования.\n" +
            "Например, в сфере мобильных программ сейчас правят\n" +
            "бал языки Swift (мобильные устройства под\n" +
            "управлением iOS) и Java (устройства под\n" +
            "управлением Android). Системные программы, как\n" +
            "правило, пишутся на языках C или {cpp}. Эти же\n" +
            "языки долгое время использовались и для создания\n" +
            "встраиваемых программ, но в последние годы в этой\n" +
            "области набирает популярность язык Java. Для\n" +
            "написания web-клиентов часто используется\n" +
            "JavaScript, а в простых случаях -- язык разметки\n" +
            "страниц HTML. Web-серверы используют опять-таки\n" +
            "Java (в сложных случаях), а также Python и PHP (в\n" +
            "более простых). Наконец, простые desktop-программы\n" +
            "сейчас могут быть написаны на самых разных языках,\n" +
            "и выбор во многом зависит от сложности программы,\n" +
            "области её использования, предполагаемой\n" +
            "операционной системы. В первую очередь следует\n" +
            "назвать языки Java, {cpp}, C#, Python, Visual\n" +
            "Basic, Ruby, Swift.\n" +
            "Самым универсальным и одновременно самым\n" +
            "распространённым языком программирования на данный\n" +
            "момент следует считать язык Java. Java в широком\n" +
            "смысле -- не только язык, но и платформа для\n" +
            "выполнения программ под самыми разными\n" +
            "операционными системами и на разной аппаратуре.\n" +
            "Такая универсальность обеспечивается наличием\n" +
            "виртуальной машины Java -- системной программы,\n" +
            "интерпретирующей Java байт-код в машинные коды\n" +
            "конкретного компьютера или системы. Java также\n" +
            "включает богатейший набор библиотек для\n" +
            "разработки.";

    // Tail examples
    private final Tail tail1 = new Tail(null, null);
    private final Tail tail2 = new Tail(10, null);
    private final Tail tail3 = new Tail(100, null);
    private final Tail tail4 = new Tail(null, 5);
    private final Tail tail5 = new Tail(null, 10);
    private final Tail tail6 = new Tail(null, 66);


    @org.junit.Test
    public void extractTail() throws Exception {
        assertEquals("", tail2.extractTail(new ByteArrayInputStream(text1.getBytes(StandardCharsets.UTF_8))));
        assertEquals("llo world!", tail2.extractTail(new ByteArrayInputStream(text2.getBytes(StandardCharsets.UTF_8))));
        assertEquals("азработки.", tail2.extractTail(new ByteArrayInputStream(text3.getBytes(StandardCharsets.UTF_8))));
        assertEquals(result1, tail1.extractTail(new ByteArrayInputStream(text3.getBytes(StandardCharsets.UTF_8))));
        assertEquals(result1, tail5.extractTail(new ByteArrayInputStream(text3.getBytes(StandardCharsets.UTF_8))));
        assertEquals(result3, tail4.extractTail(new ByteArrayInputStream(text3.getBytes(StandardCharsets.UTF_8))));
        assertEquals(result5, tail6.extractTail(new ByteArrayInputStream(text3.getBytes(StandardCharsets.UTF_8))));
    }

    @org.junit.Test
    public void fromFile() throws Exception {
        assertEquals("", tail1.fromFile("files\\input0.txt"));
        assertEquals(result2, tail3.fromFile("files\\input1.txt"));
        assertEquals(result1, tail5.fromFile("files\\input1.txt"));
        assertEquals("second).", tail2.fromFile("files\\input2.txt"));
        assertEquals(result4, tail6.fromFile("files\\input2.txt"));
    }
}