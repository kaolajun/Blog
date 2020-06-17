package com.yanjin.blog.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 利用切面记录日志
 * 记录访问被拦截方法的URL,IP,方法名
 */
//切面;组件扫描
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //切面，拦截web包下所有类的所有方法
    @Pointcut("execution(* com.yanjin.blog.web.*.*(..))")
    public void log(){
    }

    //在切面之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){

        //获取请求携带的各种参数(url,ip)
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();

        //获取访问的方法名和参数
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "."+ joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        //将参数放入RequstLog对象
        RequstLog requstLog = new RequstLog(url, ip, classMethod, args);

        logger.info("Request : {}",requstLog);
    }

    //在切面之后执行
    @After("log()")
    public void doAfter(){
        logger.info("-------------doAfter---------------");
    }

    //拦截的方法返回以后执行
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result : {}",result);
    }

    private class RequstLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequstLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
