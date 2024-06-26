package com.inventory.aspect;

import com.inventory.service.impl.ProductsSoldServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Aspect
public class MyAspect {

    Logger logger = LoggerFactory.getLogger(MyAspect.class);

    @Before("execution(* com.inventory.service.impl.ProductsSoldServiceImpl.*(..))")
    public void testing(){
            logger.info("ASPECT TESTING");
    }



}
