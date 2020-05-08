package com.skotnicki.rafal;

public class Main {

    public static void main(String[] args) {

        String schoolType = "MEN-I/38/2";
        String pathToFile = "src/main/resources/wzory_arkuszy.pdf";

        PdfSearcher pdfSearcher = new PdfSearcher(schoolType, pathToFile);

        int page = pdfSearcher.searchForSchoolSheet();
        if (page > 0) {
            PdfSaver pdfSaver = new PdfSaver(pathToFile);
            pdfSaver.setPage(page);
            pdfSaver.savePage();
        }

    }
}
