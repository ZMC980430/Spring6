import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:bean.xml")
public class JdbcTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test() {
        String insert = "INSERT INTO t_emp VALUES(NULL, ?, ?, ?)";
        String update = "UPDATE t_emp SET name=? WHERE id=?";
        String select = "SELECT * FROM t_emp WHERE id=?";
//        int a = jdbcTemplate.update(insert, "Tifa", 20, "女");
//        System.out.println(a);

        Employee employee = jdbcTemplate.queryForObject(select, new BeanPropertyRowMapper<>(Employee.class), 1);
//        Employee employee = jdbcTemplate.queryForObject(select, (rs, rowNum) -> {
//                    Employee emp = new Employee();
//                    emp.setId(rs.getInt("id"));
//                    emp.setName(rs.getString("name"));
//                    emp.setAge(rs.getInt("age"));
//                    emp.setSex(rs.getString("sex"));
//                    return emp;
//                }, 1);
        System.out.println(employee);

    }
}
