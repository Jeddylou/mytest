package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class jsoupdemo1 {
    public static void main(String[] args) throws IOException {
        String path = jsoupdemo1.class.getClassLoader().getResource("student.xml").getPath();
        Document doc = Jsoup.parse(new File(path), "utf-8");
        Elements elements = doc.getElementsByTag("name");
        Element ele = elements.get(0);
        String name = ele.text();
        System.out.println(name);
    }
}
