package transactions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import transactions.controller.BookController;
import transactions.dao.BookDao;

@SpringJUnitConfig(locations = "classpath:bean.xml")
public class TransactionTest {
    @Autowired
    private BookController bookController;

    @Test
    public void test() {
        bookController.buyBook(1, 1);
    }
}
