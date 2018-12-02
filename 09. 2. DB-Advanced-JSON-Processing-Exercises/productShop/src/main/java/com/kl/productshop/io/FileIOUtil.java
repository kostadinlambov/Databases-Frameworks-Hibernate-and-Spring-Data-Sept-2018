package com.kl.productshop.io;


import java.io.IOException;

public interface FileIOUtil {
    String readJson(String relativePath) throws IOException;

    void writeToFile(String content, String filePath) throws IOException;

    String read(String filePath) throws IOException;

    void write(String content, String filePath) throws IOException;
}
