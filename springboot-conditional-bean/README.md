# Auto Configuration
## @EnableAutoConfiguration
- Auto Configuration은 애플리케이션에서 필요한 Bean을 유추해서 구성해 주는 기능을 담당 한다.
- `@EnableAutoConfiguration` 설정은 spring-boot의 AutoConfiguration 을 사용하겠다고 선언한다.
- `@SpringBootApplication` 에 포함

## @EnableAutoConfiguration
- java configuration은 auto configuration으로 동작할 수 있음
- java configuration이 auto configuration으로 동작하기 위해서 설정 파일에 대상 Configuration 이 설정 되어야 한다.

## 2.6.x 이전
spring-boot-autoconfigure/META-INF/spring.factories 에 spring-boot 가 제공하는 모든 AutoConfiguration 이 설정되어 있음

## 2.7.x 이후
spring-boot-autoconfigure/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports

## AutoConfiguration에서 제외
- auto configuration 에서 설정을 제외하고 싶다면 `@EnableAutoConfiguration`의 exclude를 설정한다.
- `@SpringBootApplication` 을 사용한 경우도 동일한 방법으로 제외 할 수 있다.

```java
@SpringBootApplication(exclude= RedisAutoConfiguration.class)
public class StudentApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }
}
```

## Auto Configuration 예시
- `@ConditionalOnClass`, `@ConditionalOnMissingBean` 등의 어노테이션으로 설정 제어
- `EmbeddedWebServerFactoryCustomizerAutoConfiguration.java`

```java
@AutoConfiguration
@ConditionalOnWebApplication
@EnableConfigurationProperties(ServerProperties.class)
public class EmbeddedWebServerFactoryCustomizerAutoConfiguration {

	/**
	 * Nested configuration if Tomcat is being used.
	 */
	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass({ Tomcat.class, UpgradeProtocol.class })
	public static class TomcatWebServerFactoryCustomizerConfiguration {

		@Bean
		public TomcatWebServerFactoryCustomizer tomcatWebServerFactoryCustomizer(Environment environment,
				ServerProperties serverProperties) {
			return new TomcatWebServerFactoryCustomizer(environment, serverProperties);
		}

	}

	/**
	 * Nested configuration if Jetty is being used.
	 */
	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass({ Server.class, Loader.class, WebAppContext.class })
	public static class JettyWebServerFactoryCustomizerConfiguration {

		@Bean
		public JettyWebServerFactoryCustomizer jettyWebServerFactoryCustomizer(Environment environment,
				ServerProperties serverProperties) {
			return new JettyWebServerFactoryCustomizer(environment, serverProperties);
		}

	}
	// 생략
}	
```

# @Conditional
- Spring Framework 4.0 부터 제공
- 설정된 모든 Condition 인터페이스의 조건이 true 인 경우 동작

## Conditional 어노테이션
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Conditional {
  /**
  * All {@link Condition}s that must {@linkplain Condition#matches match}
  * in order for the component to be registered.
  */
  Class? extends Condition[] value();
}
```

## Condition.java
- matches 메소드의 반환 값이 true 인 경우, 동작

```java
public interface Condition {

    boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata);
}
```

## @ConditionalOnXXX
- spring-boot 가 제공하는 `@Conditional` 의 확장


|구분|내용|비고|
|:--:|:--:|:--:|
|`@ConditionalOnWebApplication`|프로젝트가 웹 애플리케이션이면 설정 동작||
|`@ConditionalOnBean`|해당 Bean이 Spring Context에 존재하면 동작|Auto configuration only|
|`@ConditionalOnMissingBean`|해당 Bean 이 Spring Context 에 존재하지 않으면 동작|Auto configuration only|
|`@ConditionalOnClass`|해당 클래스가 존재하면 자동설정 등록||
|`@ConditionalOnMissingClass`|해당 클래스가 존재하지 않으면 자동설정 등록||
|`@ConditionalOnResource`|자원이(file 등) 존재하면 동작||
|`@ConditionalOnProperty`|프로퍼티가 존재하면 동작||
|`@ConditionalOnJava`|JVM 버전에 따라 동작여부 결정||
|`@ConditionalOnWarDeployment`|전통적인 war 배포 방식에서만 동작||
|`@ConditionalOnExpression`|SpEL 의 결과에 따라 동작여부 결정||

## @ConditionalOnBean
- Bean 이 이미 설정된 경우에 동작
- MyService 타입의 Bean 이 BeanFactory 에 이미 등록된 경우에 동작 한다.
- Configuration 이 AutoConfiguration에 등록된 경우에 사용할 수 있다.

```java
@Configuration
public class MyAutoConfiguration {
  
    @ConditionalOnBean
    @Bean
    public MyService myService() {
        ...
    }
  
}
```

## @ConditionalOnMissingBean
- BeanFactory에 Bean이 설정되지 않은 경우에 동작
- MyService 타입의 Bean이 BeanFactory에 등록되지 않은 경우에 동작 한다.
- Configuration 이 AutoConfiguration에 등록된 경우에 사용할 수 있다.

```java
@Configuration
public class MyAutoConfiguration {
  
    @ConditionalOnMissingBean
    @Bean
    public MyService myService() {
        ...
    }
  
}
```