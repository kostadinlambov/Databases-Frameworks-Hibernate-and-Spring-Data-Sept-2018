package bookshopSystem.services;

import bookshopSystem.domain.entities.Author;
import bookshopSystem.repositories.AuthorRepository;
import bookshopSystem.util.io.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_FILE_PATH = "C:\\Users\\valch\\Desktop\\Java DB Projects\\01. Java DB Fundamentals-September-2018\\02. Databases Frameworks - Hibernate & Spring Data - October 2018\\06. 2. Spring-Data-Intro-Exercises\\01_Bookshop_System\\src\\main\\resources\\files\\authors.txt";
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] authorFileContent = this.fileUtil.getFileContent(AUTHORS_FILE_PATH);
        for (String line : authorFileContent) {
            String[] names = line.split("\\s+");

            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }
}
