package org.test.openpdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.html.HtmlParser;
import com.lowagie.text.pdf.PdfWriter;
import org.jsoup.Jsoup;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws IOException {
        org.jsoup.nodes.Document rawhtml =
                Jsoup.parse(new URL("http://localhost:3000/pdf").openStream(), StandardCharsets.UTF_8.name(), "http://localhost:3000/pdf");

        // step 1: creation of a document-object
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("parseHelloWorld.pdf"));
            // step 2: we open the document
            document.open();
            // step 3: parsing the HTML document to convert it in PDF
            HtmlParser.parse(document, new ByteArrayInputStream(rawhtml.outerHtml().getBytes(StandardCharsets.UTF_8)));
        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        } finally {
            document.close();
        }
    }

}
