package jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class xpass {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        String path = xpass.class.getClassLoader().getResource("student.xml").getPath();
        Document doc = Jsoup.parse(new File(path), "utf-8");

        JXDocument jxDocument = new JXDocument(doc);
//        List<JXNode> jxNodes = jxDocument.selN("//student");
//        for (JXNode jxNode : jxNodes) {
//            System.out.println(jxNode);
//        }
//            List<JXNode> jxNodes2 = jxDocument.selN("//student/name");
//            for (JXNode jxNode : jxNodes2) {
//            System.out.println(jxNode);
//            }

//        List<JXNode> jxNodes3 = jxDocument.selN("//student/name[@id]");
//        for (JXNode jxNode : jxNodes3) {
//            System.out.println(jxNode);
//        }

        List<JXNode> jxNodes4 = jxDocument.selN("//student/name[@id='itcast']");
        for (JXNode jxNode : jxNodes4) {
            System.out.println(jxNode);
        }

    }
}