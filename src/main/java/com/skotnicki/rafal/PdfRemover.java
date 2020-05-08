package com.skotnicki.rafal;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.colorspace.PdfColorSpace;
import com.itextpdf.pdfcleanup.PdfCleanUpLocation;
import com.itextpdf.pdfcleanup.PdfCleanUpTool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfRemover {

    private final String pathToFile;

    public PdfRemover(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public void removeHeader() {
        // define IO source and target
        File inputFile = new File(pathToFile);
        File outputFile = new File("src/main/resources/headless_sheet.pdf");

        // open document
        try (PdfDocument pdfDocument = new PdfDocument(new PdfReader(inputFile), new PdfWriter(outputFile))) {
            // define rectangles to be erased
            List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<>();
            Color whiteColor = new DeviceRgb(255, 255, 255);
            cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(0, 760, 600, 100), whiteColor));

            // invoke pdfSweep
            PdfCleanUpTool cleanUpTool = new PdfCleanUpTool(pdfDocument, cleanUpLocations);
            cleanUpTool.cleanUp();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
