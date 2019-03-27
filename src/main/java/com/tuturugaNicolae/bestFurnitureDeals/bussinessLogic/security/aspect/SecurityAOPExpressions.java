package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class SecurityAOPExpressions {
    @Pointcut("execution(* com.tuturugaNicolae.bestFurnitureDeals.service..*.*(..))" +
            " && !execution(* com.tuturugaNicolae.bestFurnitureDeals.service.UserService.addUser(..))" +
            "&& !execution(* com.tuturugaNicolae.bestFurnitureDeals.service.UserService.getUserByUsername(..))")
    public void servicePackageAndSubpackagePointcut() {
    }

    @Pointcut("@annotation(com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.IsStaff)")
    public void hasIsStaffAnnotation() {
    }
}
