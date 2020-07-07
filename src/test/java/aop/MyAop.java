package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: helisen
 * @create: 2020-05-10 21:15
 **/
@Aspect
@Component
public class MyAop {
	@Pointcut("execution(* aop.StudentServiceImpl.*(..))")
	public void cut(){}

	@Around("cut()")
	public Object aopMethod(ProceedingJoinPoint point) throws Throwable{
			Object[] args = point.getArgs();
			System.out.println("方法执行前增强");
			return point.proceed(args);
	}
}
