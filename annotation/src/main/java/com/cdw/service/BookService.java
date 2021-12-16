package com.cdw.service;

import com.cdw.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @author: cdw
 * @date: 2021/12/15 16:13
 * @description:
 */
@Service
public class BookService {

//    @Qualifier("bookDao")
//    @Autowired
//    @Resource(name = "bookDao2")
    @Inject
    private BookDao bookDao;

    public void print(){
        System.out.println(bookDao);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
