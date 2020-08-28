package com.lyb.dao;

import com.lyb.pojo.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    int addBlog(Blog blog);

    //查询博客
    List<Blog> queryBlogIF(Map<String,Object> map);

    List<Blog> queryBlogChoose(Map<String,Object> map);

    //更新博客
    int updateBlog(Map<String,Object> map);

    //查询第1,2,3号记录的博客
    List<Blog> queryBlogForeach(Map<String,Object> map);

    List<Blog> queryBlogForeach2(Map<String,Object> map);

    Blog queryBlogById(@Param("bid") int id);
}
