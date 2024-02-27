package com.example.QRRacun;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.zxing.client.j2se.MatrixToImageWriter.toBufferedImage;

@Component
public class GenerateQRCode {

    private static final int QR_CODE_SIZE = 200;

    public String generateQRCode(String data) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hints);
        BufferedImage qrImage = toBufferedImage(bitMatrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);
        baos.flush();
        byte[] qrBytes = baos.toByteArray();
        baos.close();

        //convert byte array to image and download it
        ByteArrayInputStream bis = new ByteArrayInputStream(qrBytes);
        BufferedImage image = ImageIO.read(bis);
        bis.close();

        File outputFile = new File("VodaNS.png");
        ImageIO.write(image, "png", outputFile);

        Desktop.getDesktop().open(outputFile);

        System.out.println("\nImage downloaded successfully.");
        return "Image downloaded successfully.";
    }
}
