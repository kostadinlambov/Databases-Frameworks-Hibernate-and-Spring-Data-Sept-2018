package com.kl.cardealer.io;

public interface JSONParser {
    public <T> T importJson(Class<T> dto, String fileName);

    public <T> void outputJson(Class<T> dto, String fileName);

    public <T> T objectFromFile(Class<T> tClass, String relativePath);

    public <T> void objectToFile(T obj, String relativePath);
}
