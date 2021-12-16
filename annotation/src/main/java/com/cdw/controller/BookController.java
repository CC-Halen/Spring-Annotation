package com.cdw.controller;

import com.cdw.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.security.PrivateKey;

/**
 * @author: cdw
 * @date: 2021/12/15 16:14
 * @description:
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
}
