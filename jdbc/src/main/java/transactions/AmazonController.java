package transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import transactions.controller.BookController;
import transactions.service.BookService;

@Controller
public class AmazonController implements BookController {
    @Autowired
    private BookService bookService;

    @Override
    public void buyBook(Integer bookId, Integer userId) {
        bookService.buy(bookId, userId);
    }
}
