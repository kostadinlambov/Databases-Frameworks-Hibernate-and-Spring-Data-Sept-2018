package com.kl.productshop.io;

import com.google.gson.Gson;

import java.io.IOException;

public class JSONParserImpl {
    private final Gson gson;
    private final FileIOUtil fileIOUtil;


    public JSONParserImpl(Gson gson, FileIOUtil fileIOUtil) throws IOException {
        this.gson = gson;
        this.fileIOUtil = fileIOUtil;
    }

    public <T> T importJson(Class<T> dto, String fileName)
            throws IOException {
        String content = this.fileIOUtil.read(fileName);
        T mapped = gson.fromJson(content, dto);
        return mapped;
    }

    public <T> void outputJson(Class<T> dto, String fileName) throws IOException {
        String content = gson.toJson(dto);
        this.fileIOUtil.write(content, fileName);
    }

//    public void outputJson(PersonDto dto, String fileName) throws IOException {
//        String content = gson.toJson(dto);
//        this.fileIOUtil.write(content, fileName);
//    }
}
