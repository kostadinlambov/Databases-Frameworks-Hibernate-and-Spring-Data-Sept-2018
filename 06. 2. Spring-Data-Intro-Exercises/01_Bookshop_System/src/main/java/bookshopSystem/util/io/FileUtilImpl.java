package bookshopSystem.util.io;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String[] getFileContent(String filePath) throws IOException {
        File file = new File(filePath);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        List<String> lines = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }

        return lines.stream().filter(l -> !l.equals("")).toArray(String[]::new);
    }
}
