package com.cdw.beans;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author: cdw
 * @date: 2021/12/15 19:50
 * @description:
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }


    @Override
    public boolean isSingleton() {
        return true;
    }
}
