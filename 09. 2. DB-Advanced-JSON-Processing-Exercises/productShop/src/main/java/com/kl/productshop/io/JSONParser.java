package com.kl.productshop.io;

public interface JSONParser {
    public <T> T importJson(Class<T> dto, String fileName);

    public <T> void outputJson(Class<T> dto, String fileName);
}
