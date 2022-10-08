package com.getdream.dao;

import com.getdream.pojo.Blog;

public interface BlogMapper {

    Blog getBlogByID(int id);

    int addBlog(Blog blog);
}
