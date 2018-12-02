package com.kl.cardealer.io;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Scanner;

public class FileIOUtilImpl implements FileIOUtil {

    public FileIOUtilImpl() throws IOException {
    }

    public String readJson(String relativePath) throws IOException {
        File file = new ClassPathResource(relativePath).getFile();
//        File file = new ClassPathResource("seed.json").getFile();

        Scanner in = new Scanner(new FileReader(file));
        StringBuilder json = new StringBuilder();
        while (in.hasNext()) {
            json.append(in.nextLine());
            json.append("\n");
        }

        return json.toString();
    }

    public void writeToFile(String content, String filePath) throws IOException {
        File file = new File(filePath);

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();
    }

    public String read(String filePath) throws IOException {
        InputStream str = getClass().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(str));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public void write(String content, String filePath) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(filePath);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            writer.write(content);
        }
    }

}
