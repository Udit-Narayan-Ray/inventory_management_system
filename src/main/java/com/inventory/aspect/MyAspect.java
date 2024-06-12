package com.inventory.aspect;

import com.inventory.service.impl.ProductsSoldServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class MyAspect {

    Logger logger = LoggerFactory.getLogger(ProductsSoldServiceImpl.class);

    @Before("execution(* com.inventory.service.impl.ProductsSoldServiceImpl.*(..))")
    private void testing(){
        logger.debug("INSIDE Sells Service Implementation");
    }

}
