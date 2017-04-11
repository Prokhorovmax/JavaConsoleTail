import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;


public class TailTest {

    private final String text1 = "Hello world!";
    private final String text2 = "???";
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

    private final Tail tail1 = new Tail(10, null);
    private final Tail tail2 = new Tail(null, 10);
    private final Tail tail3 = new Tail(null, null);

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


    @org.junit.Test
    public void extractTail() throws Exception {
        assertEquals("llo world!", tail1.extractTail(new ByteArrayInputStream(text1.getBytes(StandardCharsets.UTF_8))));
        assertEquals("???", tail1.extractTail(new ByteArrayInputStream(text2.getBytes(StandardCharsets.UTF_8))));
        assertEquals("азработки.", tail1.extractTail(new ByteArrayInputStream(text3.getBytes(StandardCharsets.UTF_8))));
        assertEquals(result1, tail2.extractTail(new ByteArrayInputStream(text3.getBytes(StandardCharsets.UTF_8))));
        assertEquals(result1, tail3.extractTail(new ByteArrayInputStream(text3.getBytes(StandardCharsets.UTF_8))));
    }

    @org.junit.Test
    public void fromFile() throws Exception {

    }

    @org.junit.Test
    public void out() throws Exception {

    }

    @org.junit.Test
    public void toFile() throws Exception {

    }

}