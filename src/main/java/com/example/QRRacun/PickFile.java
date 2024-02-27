package com.example.QRRacun;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class PickFile {
    File pickFile() {
        JFileChooser fileChooser = new JFileChooser();

        // Set filter to show only PDF files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
        fileChooser.setFileFilter(filter);

        // Show the file chooser dialog
        int returnValue = fileChooser.showOpenDialog(null);

        File selectedFile;
        // Check if the user selected a file
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No file selected.");
            selectedFile=null;
        }
        return  selectedFile;
    }
}
