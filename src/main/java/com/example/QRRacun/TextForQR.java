package com.example.QRRacun;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextForQR {

    public String getPozivNaBroj(String text) {

        //Create a pattern
        Pattern pattern = Pattern.compile("poziv[^\\r\\n]*na[^\\r\\n]*broj[^\\r\\n:]*:\\s*([^\\r\\n]*)",Pattern.CASE_INSENSITIVE);

        // Create a matcher with the given text
        Matcher matcher = pattern.matcher(text);

        // Check if the pattern is found

        if (matcher.find()) {
            // Extract the desired part of the text
            String extractedText = matcher.group(1);
            String[] parts=extractedText.split("\\u00AD");
            StringBuilder sb = new StringBuilder();
            sb.append("00");
            sb.append(parts[1]);
            sb.append(parts[0]);
            extractedText= sb.toString();
            String arrangedExtractedText = extractedText.replaceAll("[^0-9]","");
            System.out.println("\nExtracted text: " + extractedText);
            return arrangedExtractedText;
        } else {
            System.out.println("\nPattern not found in the text.");
            return null;

        }

    }

    public String getUkupnoZaPlacanje(String text) {

        //Create a pattern
        Pattern pattern = Pattern.compile("UKUPNO[^\\r\\n]*ZA[^\\r\\n]*PLAĆANJE[^\\r\\n:]*:\\s*([^\\r\\n]*)",Pattern.CASE_INSENSITIVE);

        // Create a matcher with the given text
        Matcher matcher = pattern.matcher(text);

        // Check if the pattern is found

        if (matcher.find()) {
            // Extract the desired part of the text
            String extractedText = matcher.group(1);
            String arrangedExtractedText = extractedText.replace(".",",");
            String moreArrangedExtractedText = arrangedExtractedText.replaceAll("[^0-9 ,]","");
            System.out.println("\nExtracted text: " + extractedText);
            return moreArrangedExtractedText;
        } else {
            System.out.println("\nPattern not found in the text.");
            return null;

        }

    }

    public String getBrojRacuna(String text) {

        //Create a pattern
        Pattern pattern = Pattern.compile("ERSTE[^\\r\\n]*BANKA\\s*([^\\r\\n|]*)",Pattern.CASE_INSENSITIVE);

        // Create a matcher with the given text
        Matcher matcher = pattern.matcher(text);

        // Check if the pattern is found

        if (matcher.find()) {
            // Extract the desired part of the text
            String extractedText = matcher.group(1);

            String arrangedExtractedText = extractedText.replaceAll("[^0-9]", "");
            String trimedFirstDigit=arrangedExtractedText.substring(0,3);
            String lastDigits = arrangedExtractedText.substring(3,arrangedExtractedText.length());

            // Check lenght and correct if neccessary
            if (arrangedExtractedText.length() < 18) {
                int zeroesToAdd = 18 - arrangedExtractedText.length();
                StringBuilder sb = new StringBuilder();
                sb.append(trimedFirstDigit);
                for (int i = 0; i < zeroesToAdd; i++) {
                    sb.append("0");
                }
                sb.append(lastDigits);
                arrangedExtractedText = sb.toString();
            }
            System.out.println("\nExtracted text: " + extractedText);
            return arrangedExtractedText;
        } else {
            System.out.println("\nPattern not found in the text.");
            return null;

        }

    }

    public String getSvrhaUplate(String text) {

        //Create a pattern
        Pattern pattern = Pattern.compile("datum[^\\r\\n]*izdavanja[^\\r\\n]*:[^\\r\\n.]*"+Pattern.quote(".")+"([^\\r\\n,]*)",Pattern.CASE_INSENSITIVE);

        // Create a matcher with the given text
        Matcher matcher = pattern.matcher(text);

        // Check if the pattern is found

        if (matcher.find()) {
            // Extract the desired part of the text
            String extractedText = matcher.group(1);
            System.out.println("\nExtracted text: " + extractedText);
            String svrhaUplate = "Uplata po racunu za " + extractedText;
            return svrhaUplate;
        } else {
            System.out.println("\nPattern not found in the text.");
            return null;

        }

    }

    public String getInformacijeProdavca(String text) {


        String information ="JKP VODOVOD I KANALIZACIJA\n" +
                "Masarikova 17\n" +
                "Novi Sad";

        return information;
    }



}



