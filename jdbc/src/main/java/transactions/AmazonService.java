package transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transactions.dao.BookDao;
import transactions.service.BookService;

@Service
public class AmazonService implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional
    public void buy(Integer bookId, Integer userId) {
        Integer price = bookDao.getPriceByBookId(bookId);
        bookDao.updateStock(bookId);
        bookDao.updateBalance(userId, price);
    }
}
