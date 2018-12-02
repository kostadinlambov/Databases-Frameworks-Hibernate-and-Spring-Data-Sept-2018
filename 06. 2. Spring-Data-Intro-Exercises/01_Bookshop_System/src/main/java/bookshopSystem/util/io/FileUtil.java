package bookshopSystem.util.io;

import java.io.IOException;

public interface FileUtil {

    String[] getFileContent(String filePath) throws IOException;
}
