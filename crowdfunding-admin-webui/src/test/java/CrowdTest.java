import com.lblz.crowd.beans.Admin;
import com.lblz.crowd.context.session.SessionContextHolder;
import com.lblz.crowd.mapper.AdminMapper;
import com.lblz.crowd.utils.EndecryptUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author lblz
 * @deacription
 * @date 2021/6/26 16:40
 **/
//如果在实际开发中,所有想查看数值的地方都使用sysout方式打印,会给项目上线运行带来问题!
// ysout本质上是一个10操作,通常10的操作是比较消耗性能的。如果项目中sysout很多,
// 那么对性能的影响就比较大了,即使上线前专门花时间删除代码中的sysout,也很可能有遗漏,
// 而且非常麻烦。而如果使用日志系统,那么通过日志级别就可以批量的控制
//在类上标记必要的注解, Spring整合Junit
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();
        Admin admin = new Admin();
        admin.setPassword(EndecryptUtils.get3DESEncrypt("1",null));
        adminMapper.insert(admin);
        System.out.println("???啊哈"+connection);
    }

//    @Test
//    public void saveUser(){
//        User user = new User();
//        user.setId(1);
//        user.setUserName("江小鱼123");
//        Integer users = userMapper.updateById(user);
//        System.out.println("哈哈"+users);
//        throw new NullPointerException("");
//    }

    @Test
    public void t2(){
        //1、获取logger对象
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        //2、根据不同日志权限打印
        logger.debug("debug");
        logger.info("输出");
        logger.warn("警告");
        logger.error("错误");
    }

    public static void main(String[] args) {
        SessionContextHolder<CrowdTest> sessionContextHolder = new SessionContextHolder<>();
    }
}
