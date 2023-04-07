import aop.interfaces.Calculator1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:bean.xml")
public class JunitTest {
    @Autowired
    private Calculator1 calculator1;

    @Test
    public void test() {
        calculator1.nothing();
    }
}
