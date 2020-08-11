package de.braeluca;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

/**
 * @author luca.braeutigam
 */

@MicronautTest(transactional = false)
public class NamedDatasourceTest {

    @Inject
    private DemoRepository demoRepository;

    @Test
    void test() {
        Book book = new Book();
        book.setId(1L);
        book.setName("Test1");
        demoRepository.save(book);
        demoRepository.findByTitle("Test1");
    }
}
