package org.test.flyingsaucer;

import com.lowagie.text.DocumentException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        
        String outputFile = "generated.pdf";

        org.jsoup.nodes.Document rawhtml =
                Jsoup.parse(new URL("http://localhost:3000/pdf").openStream(), StandardCharsets.UTF_8.name(), "http://localhost:3000/pdf");
        rawhtml.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(rawhtml.html());
        renderer.layout();

        try (OutputStream os = Files.newOutputStream(Paths.get(outputFile))) {
            renderer.createPDF(os);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
