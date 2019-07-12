package com.tga.rollcall.aspect;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import lombok.extern.slf4j.Slf4j;

/**
 * 实现每个接口的响应时间打印
 * 
 * @author tianguoao
 *
 */
@Aspect
@Component
@Slf4j
public class RunTimeAspect {
    /**
     */
    private static final long PRINT_VALUE = 3000;

    /**
     * 定义一个切入点
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值. ~ 第二个 * 定义在web包或者子包 ~ 第三个 * 任意方法 ~ .. 匹配任意数量的参数.
     */
    //切入service层
    private static final String P_CUT_SERVICE_STR =
            "execution(* com.tga.rollcall.service.*..*(..)) ";
    //切入 controller 层
    private static final String CUT_CONTROLLER_STR =
            "execution(* com.tga.rollcall.controller.*..*(..))";
    //切入 dao 层
    private static final String CUT_DAO_STR =
            "execution(* com.tga.rollcall.dao.*..*(..))";

    @Pointcut(value = CUT_CONTROLLER_STR)
    public void logPointCut() {}

    /**
     * 环绕切面
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        try {
            ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
            if(null == requestAttributes){
                return null;
            }
            HttpServletRequest request = requestAttributes.getRequest();
            String requestUrl=request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
            if (result == null) {
                // 如果切到了 没有返回类型的void方法，这里直接返回
                return null;
            }
            long end = System.currentTimeMillis();
            // 获取当前切入类的包名
            // String targetClassName = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            // 参数
            // Object[] args = joinPoint.getArgs(); int argsSize = args.length; StringBuilder
            // argsTypes = new StringBuilder();
            //
            // String typeStr = joinPoint.getSignature().getDeclaringType().toString().split("")[0];
            // String returnType = joinPoint.getSignature().toString().split(" ")[0];
            // log.info("类/接口:" + targetClassName + "(" + typeStr + ")" + "方法：" + methodName);
            // if (argsSize > 0) {
            // // 拿到参数的类型
            // for (Object object : args) {
            // argsTypes.append(object.getClass().getTypeName()).append(" ");
            // }
            // log.info("参数类型：" + argsTypes);
            // }
            
            long diffTime = (end - start);
            //大于 1秒 打印 接口响应时间
            if (diffTime >= PRINT_VALUE) {
                log.warn("Api RunTimeInfo >>> requestUrl:{}, methodName:{},  RunTime:{} ms ",requestUrl, methodName, diffTime);
            } else {
                log.debug("debug Api RunTimeInfo >>>  requestUrl:{},  methodName:{},  RunTime:{} ms ", requestUrl,methodName, diffTime);
            }
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            log.warn("**************** around:{},   Use time:{} ms , with exception:{} ", joinPoint,
                    (end - start), e.getMessage());
            log.error("doAround error:{}", e);
            return result;
        }
    }

    /**
     * 前置切面 对Controller下面的方法执行前进行切入，初始化开始时间
     * @param jp
     */
//    @Before(value = CUT_CONTROLLER_STR)
//    public void beforeMethod(JoinPoint jp) {
//        try {
//            log.info("******************************   beforeMethod  ");
//            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//            String requestUrl=request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
//            log.info("********************  requestUrl:{}",requestUrl);
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//    }
    
//    @After(value = CUT_CONTROLLER_STR)
//    public void afterMethod(JoinPoint jp) {
//        try {
//            log.info("******************************   afterMethod  ");
//            MDC.clear();
//        } catch (Exception e) {
//            log.error("afterMethod  error:{}",e);
//        }
//    }

}
