package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class SecurityAOPExpressions {
    @Pointcut("execution(* com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service..*.*(..))" +
            " && !execution(* com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service.UserService.addUser(..))" +
            "&& !execution(* com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.service..getAll*(..))")
    public void servicePackageAndSubpackagePointcut() {
    }

    @Pointcut("@annotation(com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.IsStaff)")
    public void hasIsStaffAnnotation() {
    }
}
