package com.kl.cardealer.io;

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


    public <T> T objectFromFile(Class<T> tClass, String relativePath) {
        return this.gson.fromJson(TextFileUtils.read(relativePath), tClass);
    }

    public <T> void objectToFile(T obj, String relativePath) {
        TextFileUtils.write(objectToJson(obj), relativePath);
    }

    public <T> T objectFromJson(Class<T> tClass, String json) {
        return this.gson.fromJson(json, tClass);
    }

    public <T> String objectToJson(T obj) {
        return this.gson.toJson(obj);
    }

}
