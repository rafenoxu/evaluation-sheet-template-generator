package com.skotnicki.rafal;

import org.apache.pdfbox.multipdf.PageExtractor;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class PdfSaver {

    private final String pathToSourceFile;
    private int page;

    public PdfSaver(String pathToSourceFile) {
        this.pathToSourceFile = pathToSourceFile;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void savePage() {
        try {
            extractPage().save(new File("src/main/resources/sheet.pdf"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private PDDocument extractPage() throws IOException {
            PDDocument sourceDocument = PDDocument.load(new File(pathToSourceFile));
            PageExtractor pageExtractor = new PageExtractor(sourceDocument, page, page);
            return pageExtractor.extract();
    }
}
