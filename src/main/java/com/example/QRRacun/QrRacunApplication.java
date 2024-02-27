package com.example.QRRacun;

import com.google.zxing.WriterException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class QrRacunApplication {

	public static void main(String[] args) {


		File selectedFile;
		String fileContent;

		PickFile pickFile = new PickFile();
		selectedFile = pickFile.pickFile();

		GetTextFromPDF getTextFromPDF = new GetTextFromPDF();
		fileContent = getTextFromPDF.extractContent(selectedFile);

		System.out.print(fileContent);

		TextForQR textForQR = new TextForQR();
		System.out.print(textForQR.getPozivNaBroj(fileContent));
		System.out.print(textForQR.getUkupnoZaPlacanje(fileContent));
		System.out.print(textForQR.getBrojRacuna(fileContent));
		System.out.print(textForQR.getSvrhaUplate(fileContent));
		System.out.print(textForQR.getInformacijeProdavca(fileContent));

		String QRString = "K:PR|V:01|C:1|" +
				"R:" + textForQR.getBrojRacuna(fileContent) +
				"|N:" + textForQR.getInformacijeProdavca(fileContent) +
				"|I:RSD" + textForQR.getUkupnoZaPlacanje(fileContent) +
				"|SF:289|S:" + textForQR.getSvrhaUplate(fileContent) +
				"|RO:" + textForQR.getPozivNaBroj(fileContent);


		System.out.print("QR STRING:"+QRString);

		GenerateQRCode generateQRCode = new GenerateQRCode();

		try {
//			String isSuccessfully = generateQRCode.generateQRCode("K:PR|V:01|C:1|" +
//					"R:3401101819050|N:JAVNO KOMUNALNO PREDUZEĆE VODOVOD I KANALIZACIJA" +
//					"Masarikova 17" +
//					"Novi Sad|I:RSD4000,00|SF:289|S:Uplata po racunu za 12.2023|RO:8726589736948");
			String isSuccessfully = generateQRCode.generateQRCode(QRString);
		} catch (WriterException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);

		}


	}
}

