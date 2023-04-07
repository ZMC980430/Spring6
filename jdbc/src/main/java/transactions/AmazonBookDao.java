package transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import transactions.dao.BookDao;

@Repository
public class AmazonBookDao implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Integer getPriceByBookId(Integer bookId) {
        String selectSql = "SELECT price FROM spring.t_book WHERE book_id = ?";
        return jdbcTemplate.queryForObject(selectSql, Integer.class, bookId);
    }

    @Override
    public void updateStock(Integer bookId) {
        String updateSql = "UPDATE spring.t_book SET stock = stock - 1 WHERE book_id = ?";
        jdbcTemplate.update(updateSql, bookId);
    }

    @Override
    public void updateBalance(Integer userId, Integer price) {
        String updateSql = "UPDATE spring.t_user SET balance = balance-? WHERE user_id=?";
        jdbcTemplate.update(updateSql, price, userId);
    }

}
