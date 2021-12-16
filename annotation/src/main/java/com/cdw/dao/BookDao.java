package com.cdw.dao;

import org.springframework.stereotype.Repository;

/**
 * @author: cdw
 * @date: 2021/12/15 16:13
 * @description:
 */
@Repository
public class BookDao {

    private String label="1";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
