package br.com.esocial.configuration;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ESocialControllerAspect {
	
//	private static final Logger logger = LogManager.getLogger(ESocialControllerAspect.class);
	
	/**
	 * 1
	 */
//	@Before(value = "execution(* br.com.esocial.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.GetMapping)")
	public void beforeAdvice(JoinPoint joinPoint) {
//	    logger.debug("Debugging log");
//        logger.info("Info log");
//        logger.warn("Hey, This is a warning!");
//        logger.error("Oops! We have an Error. OK");
//        logger.fatal("Damn! Fatal error. Please fix me.");
//		   logger.info(" ###### Returning for class : {} ; Method : {} ", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
//		logger.info("[Aspect - Before] Assinatura método: " + joinPoint.getSignature());
		
		Object[] args = joinPoint.getArgs();
		for(Object arg : args) {
			String nomeClasse = (String) arg.getClass().getSimpleName();
			if(nomeClasse.contains("Form")) {
//				if(nomeClasse.contains("Entity") || nomeClasse.contains("Form")) {
//				logger.info("[Aspect - Before] Parâmetros/Argumentos " + nomeClasse + ": " + ReflectionUtil.obterCamposValores(arg)); 
	     	}
		}
 	}
	
	/**
	 * 2
	 */
//	@After(value = "execution(* br.com.esocial.controller.PlayerController.*(..))")		
	public void afterAdvice(JoinPoint joinPoint) {
//		logger.info("[Aspect - After] Assinatura método: " + joinPoint.getSignature());
	}

	
	/**
	 * 3
	 */
//	@AfterReturning(value = "execution(* br.com.esocial.controller.PlayerController.*(..))", returning = "result" )		
	public void afterAdvice(JoinPoint joinPoint, Object result) {
//		logger.info("[Aspect - AfterReturning] Assinatura método: " + joinPoint.getSignature() + " Retorno: " + result);
	}
	
//    @AfterThrowing (value = "execution(* br.com.esocial.controller..*(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(JoinPoint joinPoint, Throwable ex) throws Throwable { 
//    	logger.error("[Aspect - AfterThrowing] Assinatura método: " + joinPoint.getSignature() + " Exception: ", ex );
    	
    }
	
}