package com.skotnicki.rafal;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfSearcher {

    private final String schoolType;
    private final String pathToFile;

    public PdfSearcher(String schoolType, String pathToFile) {
        this.schoolType = schoolType;
        this.pathToFile = pathToFile;
    }

    public int searchForSchoolSheet() {
        try (PDDocument document = loadFile()) {
            return search(document, schoolType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private PDDocument loadFile() throws IOException {
        PDDocument document = PDDocument.load(new File(pathToFile));
        AccessPermission accessPermission = document.getCurrentAccessPermission();
        if (!accessPermission.canExtractContent()) {
            throw new IOException("You do not have permission to extract text");
        }
        return document;
    }

    private int search(PDDocument document, String schoolType) {
        try {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            for (int page = 1; page <= document.getNumberOfPages(); page++) {
                // Extract only one page
                stripper.setStartPage(page);
                stripper.setEndPage(page);

                String text = stripper.getText(document);
                if (text.contains(schoolType)) {
                    return page;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
