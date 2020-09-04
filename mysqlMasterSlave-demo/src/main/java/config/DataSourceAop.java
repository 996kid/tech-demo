package config;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class DataSourceAop {
    
    private final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);
    
    //公共参数，创建时间
    private static final String CREATE_DATE = "createDate";
    //修改时间
    private static final String UPDATE_DATE = "updateDate";
    
    /**
     * 只读方法的设置
     */
    @Pointcut("execution(* service.select*(..))"
            + "|| execution(* service.find*(..))"
            + "|| execution(* service.get*(..))")
    public void readonlyPointcut(){
        
    }
    
    /**
     * 读写方法的设置
     */
    @Pointcut("execution(* service.save*(..))"
            + "|| execution(* service.update*(..))"
            + "|| execution(* service.insert*(..))"
            + "|| execution(* service.add*(..))")
    public void writePointcut(){
        
    }
    
    /**
     * 具体切入
     * @param joinPoint
     */
    @Before("writePointcut()")
    public void setDataBaseMaster(JoinPoint joinPoint) {
        DynamicDataSource.setDataBaseType(DynamicDataSource.DatabaseType.primaryDataSource);
        
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();        
            
          Object[] args = joinPoint.getArgs();
    
          if (args != null && args.length > 0) {
              Object argument = args[0];
              BeanWrapper beanWrapper = new BeanWrapperImpl(argument);
              logger.info("----------主库------" + signature.getMethod().getName());
              //新增时设置创建时间
              if(signature.getMethod().getName().contains("add") 
                      || signature.getMethod().getName().contains("save") 
                      || signature.getMethod().getName().contains("insert")){
                // 设置创建时间和修改时间
                if (beanWrapper.isWritableProperty(CREATE_DATE)) {
                    beanWrapper.setPropertyValue(CREATE_DATE, new Date());
                }
              }
              //修改时间设置
              if (beanWrapper.isWritableProperty(UPDATE_DATE)) {
                  beanWrapper.setPropertyValue(UPDATE_DATE, new Date());
              }          
          }
    }
    
    /**
     * 具体切入
     * @param joinPoint
     */
    @Before("readonlyPointcut()")
    public void setDataBaseSlave(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); 
        DynamicDataSource.setDataBaseType(DynamicDataSource.DatabaseType.readonlyDataSource);
        logger.info("----------从库------" + signature.getMethod().getName());
    }
}