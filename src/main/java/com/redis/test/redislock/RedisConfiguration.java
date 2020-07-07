package com.redis.test.redislock;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(RedisLock.class)//容器中有指定的Bean   OnBeanCondition.class
//@ConditionalOnClass//当类路径下有指定的类  OnClassCondition.class
//@ConditionalOnExpression//基于SpEL表达式作为判断条件  OnExpressionCodition.class
//@ConditionalOnJava//基于JVM版本作为判断条件           OnJavaCondition.class
//@ConditionalOnJndi//在JNDI存在的条件下查找指定的位置  OnJndiCondition.class
//@ConditionalOnMissingBean//当容器中没有指定Bean的情况下  OnBeanCondition.class
//@ConditionalOnMissingClass//当类路径下没有指定的类       OnClassCondition.class
//@ConditionalOnNotWebApplication//当前项目不是Web项目     OnWebApplicationCondition.class
//@ConditionalOnProperty//配置文件中指定的属性是否有指定的值      OnPropertyCondition.class
//@ConditionalOnResource//类路径下是否有指定的资源                OnResourceCondition.class
//@ConditionalOnSingleCandidate//当指定Bean在容器中只有一个，或者虽然有多个但是指定首选Bean     OnBeanCondition.class
//@ConditionalOnWebApplication//当前项目是Web项目的条件下   OnWebApplicationCondition
public class RedisConfiguration {

}
