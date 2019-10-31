package com.eastwood.autoconfiguration.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 996kid
 * @desription
 * @date 2019/10/31
 */
public class CustomSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {"com.eastwood.autoconfiguration.entity.Kid"};
    }
}
