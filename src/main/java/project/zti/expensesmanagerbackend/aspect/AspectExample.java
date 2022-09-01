package project.zti.expensesmanagerbackend.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AspectExample {

    @After("execution(* getAllPayments(..))")
    public void afterGetAllPayments(JoinPoint joinPoint) {
        System.out.println("All payments sent");
    }

    @After("execution(* getOnePayment(..))")
    public void afterGetOnePayments(JoinPoint joinPoint) {
        System.out.println("One payment sent");
    }

    @After("execution(* getAllPaymentsFromRange(..))")
    public void afterGetAllPaymentsFromRange(JoinPoint joinPoint) {
        System.out.println("All payments from given range sent");
    }

    @After("execution(* updatePayment(..))")
    public void afterUpdatePayment(JoinPoint joinPoint) {
        System.out.println("Payment updated");
    }

    @After("execution(* deletePayment(..))")
    public void afterDeletePayment(JoinPoint joinPoint) {
        System.out.println("Payment deleted");
    }
}