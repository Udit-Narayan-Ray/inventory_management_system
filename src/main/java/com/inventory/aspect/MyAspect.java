package com.inventory.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {

    @Before("execution(* com.inventory.service.impl.ProductsSoldServiceImpl.*(..))")
    private void testing(){
        System.out.println("INSIDE ASPECT ORIENTED PROGRAMMING");
    }
}
