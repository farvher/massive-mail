package com.massivemail.massivemailing.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public static String readFileAsString(MultipartFile file) {
		StringBuffer fileData = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			char[] buf = new char[1024];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("No se pudo convertir el archivo");
		}

		return fileData.toString();
	}

}
