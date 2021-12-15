package com.cdw.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: cdw
 * @date: 2021/12/15 19:20
 * @description:
 */
public class MyImportSelector implements ImportSelector {
    /**
     *
     * @param importingClassMetadata
     * @return 要导入到容器中的组件全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.cdw.beans.Blue"};
    }
}
