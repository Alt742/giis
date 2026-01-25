package org.webvane.intapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {
    "classpath:springcontext/common.xml",
    "classpath:springcontext/dictionary.xml",
    "classpath:springcontext/annotationsCache.xml",
    "classpath:springcontext/frameworkbase.xml",
    "classpath:springcontext/framework.xml",

//    "classpath:springcontext/user-authorizer.xml",
    "classpath:springcontext/intapp.acl.xml",
    "classpath:springcontext/intapp.starter.xml",

    "classpath:springcontext/jms/fw.jms.adaptor.xml",
    "classpath:springcontext/jms/fw.jms.embedded2.xml",
    "classpath:springcontext/fw.bean.queue.adaptor.xml",

    "classpath:springcontext/giisdmdk.xml",

    "classpath:springcontext/giisdmdk.demons.xml"
})
@ComponentScan(basePackages = {
    "org.webvane.framework.mvc",
    "org.webvane.dictionary.mvc"
})
public class ImportConfiguration {
}
