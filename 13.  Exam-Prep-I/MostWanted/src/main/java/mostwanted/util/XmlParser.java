package mostwanted.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParser {

    <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException, FileNotFoundException;

    <O> void exportXml(O object, Class<O> objectClass, String path) throws JAXBException;
}
