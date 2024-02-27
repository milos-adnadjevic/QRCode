package com.example.QRRacun;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class GetTextFromPDF {
    public String extractContent(final File multipartFile) {
        String text;

        try (final PDDocument document = Loader.loadPDF((File) multipartFile)) {
            final PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
        } catch (final Exception ex) {
            new Exception("Error parsing PDF", ex);
            text = "Error parsing PDF";
        }

        return text;


    }
}
