import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

/**
 * Created by GFH on 24.03.2015.
 */
@Aspect
public class MethodLogger {
    private final Logger logger = LoggerFactory.getLogger(MethodLogger.class);
    @Around("execution(* *(..)) && @annotation(Loggable)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        logger.info(String.format(
                "#%s(%s): %s in %s",
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                Arrays.toString(point.getArgs()),
                result,
                System.currentTimeMillis() - start)
        );
        return result;
    }
}
