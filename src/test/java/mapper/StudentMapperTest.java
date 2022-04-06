package mapper;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import data.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Project name(项目名称)：Mybatis_Plus实现插入操作
 * Package(包名): mapper
 * Class(测试类名): StudentMapperTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/6
 * Time(创建时间)： 12:57
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */


class StudentMapperTest
{
    /**
     * Insert.
     *
     * @throws IOException the io exception
     */
    @Test
    public void insert() throws IOException
    {
        String resource = "mybatis-config.xml";
        //读取配置文件mybatis-config.xml
        InputStream config = Resources.getResourceAsStream(resource);
        //根据配置文件构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(config);
        //通过SqlSessionFactory创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Student student = new Student();
        student.setName("张三");
        student.setSex("男");
        student.setClass_no(1001L);
        student.setAddress("中国");
        student.setBirthday("2001");
        student.setId_card("56879955");

        int insert = studentMapper.insert(student);
        System.out.println(insert);

        sqlSession.commit();

        sqlSession.close();
    }

    /**
     * Select.
     *
     * @throws IOException the io exception
     */
    @Test
    public void select() throws IOException
    {
        String resource = "mybatis-config.xml";
        //读取配置文件mybatis-config.xml
        InputStream config = Resources.getResourceAsStream(resource);
        //根据配置文件构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(config);
        //通过SqlSessionFactory创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "张三");
        List<Student> list = studentMapper.selectList(queryWrapper);
        for (Student student : list)
        {
            System.out.print(student);
        }
    }

    /**
     * Insert 1.
     *
     * @throws IOException the io exception
     */
    @Test
    public void insert1() throws IOException
    {
        String resource = "mybatis-config.xml";
        //读取配置文件mybatis-config.xml
        InputStream config = Resources.getResourceAsStream(resource);
        //根据配置文件构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(config);
        //通过SqlSessionFactory创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Student student = new Student();
        student.setName("张三");
        student.setSex("男");
        student.setClass_no(1001L);
        student.setAddress("中国");
        student.setBirthday("2001");
        student.setId_card("56879955");
        //Cause: java.sql.SQLSyntaxErrorException: Unknown column 'tel' in 'field list'
        //@TableField(value = "telephone_number")
        student.setTel("12358785655");

        int insert = studentMapper.insert(student);
        System.out.println(insert);

        sqlSession.commit();

        sqlSession.close();
    }
}