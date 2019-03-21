package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.aspect;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.exception.UnauthorizedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {
    private SecurityContext securityContext;

    @Autowired
    public SecurityAspect(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Around("com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.aspect.SecurityAOPExpressions.servicePackageAndSubpackagePointcut()")
    public Object checkIfUserIsLogged(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (securityContext.getLoggedUser().get() == null) {
            throw new UnauthorizedException();
        }
        Object returnValue;
        try {
            returnValue = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        }
        return returnValue;
    }
}
