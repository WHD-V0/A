package com.example.demo.aspect;


import com.example.demo.annotation.LogAnnotation;
import com.example.demo.entity.Log;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
// 导入Arrays类（用于处理数组相关操作）
import java.lang.reflect.Method;
import java.util.Arrays;
// 导入LocalDateTime类（用于处理日期时间）
import java.time.LocalDateTime;
@Aspect
@Component
public class LogAspect {
    // 记录开始时间
    private ThreadLocal<Long> startTime = new ThreadLocal<>();
    // 日志对象
    private ThreadLocal<Log> logThreadLocal = new ThreadLocal<>();

    // 切入点：匹配controller层所有方法
    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    public void logPointCut() {}

    // 前置通知：记录请求信息
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());

        Log log = new Log();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 设置请求IP
        log.setIp(request.getRemoteAddr());
        // 设置请求方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        // 设置请求参数
        log.setParams(Arrays.toString(joinPoint.getArgs()));
        // 设置操作时间
        log.setCreateTime(LocalDateTime.now());
        // TODO: 实际项目中可从登录信息中获取用户名
        log.setUsername("当前登录用户");
        // 设置操作描述（可通过自定义注解实现更灵活的描述）
        log.setOperation("访问" + log.getMethod() + "方法");

        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        if (logAnnotation != null) {
            log.setOperation(logAnnotation.value()); // 使用注解中的描述
        } else {
            log.setOperation("访问" + log.getMethod() + "方法"); // 默认描述
        }

        logThreadLocal.set(log);
    }

    // 后置通知：记录请求结果
    @AfterReturning(returning = "result", pointcut = "logPointCut()")
    public void doAfterReturning(Object result) {
        Log log = logThreadLocal.get();
        // 计算请求耗时
        long time = System.currentTimeMillis() - startTime.get();

        // 这里可以将日志信息保存到数据库或输出到日志文件
        System.out.println("日志信息：" + log.toString() + "，耗时：" + time + "ms");

        // 清除ThreadLocal
        startTime.remove();
        logThreadLocal.remove();
    }

}
