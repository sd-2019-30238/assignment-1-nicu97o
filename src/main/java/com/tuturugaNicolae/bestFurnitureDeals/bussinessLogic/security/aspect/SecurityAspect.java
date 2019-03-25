package com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.aspect;

import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.dto.model.UserTypeDTO;
import com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.SecurityContext;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.User;
import com.tuturugaNicolae.bestFurnitureDeals.databaseAccess.entity.UserType;
import com.tuturugaNicolae.bestFurnitureDeals.exception.ForbiddenException;
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
        if (!securityContext.isAuthenticated()) {
            throw new UnauthorizedException();
        }
        return runMethod(proceedingJoinPoint);
    }

    @Around("com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.aspect.SecurityAOPExpressions.hasIsStaffAnnotation() && com.tuturugaNicolae.bestFurnitureDeals.bussinessLogic.security.aspect.SecurityAOPExpressions.servicePackageAndSubpackagePointcut()")
    public Object checkIfLoggedUserHasCorrectRole(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (!securityContext.isAuthenticated()) {
            throw new UnauthorizedException();
        }
        User loggedUser = securityContext.getLoggedUser().get();
        if (loggedUser.getUserType() != UserType.STAFF) {
            throw new ForbiddenException();
        }
        return runMethod(proceedingJoinPoint);
    }

    private Object runMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnValue;
        try {
            returnValue = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        }
        return returnValue;
    }
}
