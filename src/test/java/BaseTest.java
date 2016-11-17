

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration({ "classpath:spring-config.xml", "classpath:spring-mybatis-config.xml" }) // 加载配置文件
/**
 * 测试基础类（单元测试请先基础这个类）
 * @author renlinggao
 * @Date 2016年9月13日
 */
public abstract class BaseTest {
}
