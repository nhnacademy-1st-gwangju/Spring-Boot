package com.nhnacademy.conditional;

import com.nhnacademy.conditional.component.SayNoComponent;
import com.nhnacademy.conditional.component.SayYesComponent;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
public class ConditionalDemoConfiguration {

    static class PropertyCheckCondition implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            // TODO (1)  application.properties 파일에 test1 property 가 존재하면 true 를 반환하도록 수정하세요.

            // Hint property 가져 오기 conditionContext.getEnvironment().getProperty("test1")
            return conditionContext.getEnvironment().containsProperty("test1");

        }
    }

    /**
     * PropertyCheckCondition 이 true 를 반환하면 sayYesComponent 를 생성한다.
     *
     * @return
     */
    @Bean
    @Conditional(PropertyCheckCondition.class)
    public SayYesComponent sayYesComponent() {
        return new SayYesComponent("Conditional");
    }


    //TODO (2) 이 프로젝트가 web application 이 되도록 pom.xml의 의존성을 수정해 주세요.
    /**
     * 이 프로젝트가 web application 이면 sayYesComponentWeb 빈을 생성한다.
     *
     * @return
     */
    @ConditionalOnWebApplication
    @Bean
    public SayYesComponent sayYesComponentWeb() {
        return new SayYesComponent("ConditionalOnWebApplication");
    }

    /**
     * 이 프로젝트가  web application 이 아니면 sayYesComponentNotWeb 빈을 생성한다.
     *
     * @return
     */
    @ConditionalOnNotWebApplication
    @Bean
    public SayYesComponent sayYesComponentNotWeb() {
        return new SayYesComponent("ConditionalOnNotWebApplication");
    }


    /**
     *  SayNoComponent.class 타입의 빈이 등록되어 있으면 동작하도록하는  @ConditionalOnBean annotation 선언되어 있다.
     *
     * TODO (3) sayYesComponentOnBean 빈이 생성되도록 SayNoComponent 빈을 선언해 주세요.
     */
    @Bean
    public SayNoComponent sayNoComponent() {
        return new SayNoComponent("");
    }

    /**
     * 프로젝트에 SayNoComponent 타입의 빈이 등록되어 있으면 아래 빈을 생성합니다.
     *
     * @return
     */
    @ConditionalOnBean(SayNoComponent.class)
    @Bean
    public SayYesComponent sayYesComponentOnBean() {
        return new SayYesComponent("ConditionalOnBean");
    }

    /**
     * 프로젝트에 SayNoComponent 타입의 빈이 등록되어 있지 않으면 아래 빈을 생성합니다.
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SayNoComponent.class)
    public SayYesComponent sayYesComponentOnMissingBean() {
        return new SayYesComponent("ConditionalOnMissingBean");
    }


    /**
     * Yes 라는 이름의 클래스가 존재하면 빈을 생성하는 @ConditionalOnClass annotation이 선언되어 있습니다.
     *
     * TODO (4) sayYesComponentOnClass 빈이 생성되도록 No 클래스의 이름을 수정해 주세요.
     *
     * @return
     */
    @Bean
    @ConditionalOnClass(name={"com.nhnacademy.conditional.component.Yes"})
    public SayYesComponent sayYesComponentOnClass() {
        return new SayYesComponent("ConditionalOnClass");
    }

    @Bean
    @ConditionalOnMissingClass(value={"com.nhnacademy.conditional.component.No"})
    public SayYesComponent sayYesComponentOnMissingClass() {
        return new SayYesComponent("ConditionalOnMissingClass");
    }

    /**
     * TODO (5) test2 property가 존재하면 빈을 생성하도록 @ConditionalOnProperty annotation을 적절히 선언해 주세요.
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name="test2")
    public SayYesComponent sayYesComponentOnProperty() {
        return new SayYesComponent("ConditionalOnProperty");
    }

    /**
     * TODO (6) test.txt 파일이 존재하면 빈을 생성하는 @ConditionalOnResource annotation이 선언되어 있습니다.
     * TODO (6) sayYesComponentOnResource 빈이 생성되도록 src/main/resources 디렉토리에 test.txt 파일을 생성해 주세요.
     *
     * @return
     */
    @Bean
    @ConditionalOnResource(resources = {"classpath:/test.txt"})
    public SayYesComponent sayYesComponentOnResource() {
        return new SayYesComponent("ConditionalOnResource");
    }

}

