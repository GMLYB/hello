package com.lyb.dao;

import com.lyb.pojo.Blog;
import com.lyb.utils.IDUtils;
import com.lyb.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class BlogMapperTest {
    @Test
    public void addInitBlog(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setId(IDUtils.getId());
        blog.setTitle("所望隔山海");
        blog.setAuthor("lyb");
        blog.setCreateTime(new Date());
        blog.setViews(999);

        System.out.println(mapper.addBlog(blog));

        blog.setId(IDUtils.getId());
        blog.setTitle("山海皆可平");
        System.out.println(mapper.addBlog(blog));


        blog.setId(IDUtils.getId());
        blog.setTitle("难平是人心");
        System.out.println(mapper.addBlog(blog));

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void queryBlogIF(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map<String,Object> map = new HashMap<>();

//        map.put("title","山海皆可平");
        map.put("author","lyb");

        List<Blog> list = mapper.queryBlogIF(map);
        list.forEach(System.out::println);
    }

    @Test
    public void queryBlogChoose(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map<String,Object> map = new HashMap<>();

//        map.put("title","山海皆可平");
//        map.put("author","lyb");
        map.put("views",5000);
        List<Blog> list = mapper.queryBlogChoose(map);
        list.forEach(System.out::println);
    }

    @Test
    public void updateBlog(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map<String,Object> map = new HashMap<>();

        map.put("title","山海皆可平3");
//        map.put("author","lyb");
        map.put("id","4a448da4dbc443e78d7d80deac6ef6a7");
        System.out.println(mapper.updateBlog(map));
        sqlSession.commit();
    }


    @Test
    public void queryBlogForeach(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map<String,Object> map = new HashMap<>();

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        map.put("ids",ids);
//        List<Blog> list = mapper.queryBlogForeach(map);
        List<Blog> list = mapper.queryBlogForeach2(map);
        list.forEach(System.out::println);

    }



    @Test
    public void queryBlogById(){
        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();


        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);


        Blog blog1 = mapper1.queryBlogById(1);
//        Blog blog2 = mapper1.queryBlogById(2);
        sqlSession1.close();

        BlogMapper mapper2 = sqlSession2.getMapper(BlogMapper.class);

        Blog blog3 = mapper2.queryBlogById(1);

//        Blog blog4 = mapper2.queryBlogById(2);

        System.out.println(blog1);
//        System.out.println(blog2);
        System.out.println(blog3);
//        System.out.println(blog4);
        sqlSession2.close();

    }
}
