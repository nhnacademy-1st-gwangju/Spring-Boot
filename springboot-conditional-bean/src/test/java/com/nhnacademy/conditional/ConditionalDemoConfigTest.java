package com.nhnacademy.conditional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * 아래의 모든 테스트 케이스가 성공할 수 있도록 프로젝트를 수정해 주세요.
 */
@SpringBootTest
class ConditionalDemoConfigTest {

    private final ApplicationContextRunner contextRunner
        = new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(ConditionalDemoConfiguration.class));


    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 이 테스트를 성공하려면 TODO(1) 를 수정하세요.
     */
    @Test
    void sayYesComponent() {
        this.contextRunner
            .withPropertyValues("test1=zbum")
            .run(context -> assertThat(context).hasBean("sayYesComponent"));
    }

    /**
     * 이 테스트를 성공하려면 TODO(2) 를 수정하세요.
     */
    @Test
    public void sayYesComponentWeb() {
        assertTrue(this.applicationContext.containsBean("sayYesComponentWeb"));
        assertFalse(this.applicationContext.containsBean("sayYesComponentNotWeb"));
    }


    /**
     * 이 테스트를 성공하려면 TODO(3) 를 수정하세요.
     */
    @Test
    public void sayYesComponentOnBean() {
        this.contextRunner
            .run(context -> assertThat(context).hasBean("sayYesComponentOnBean"));
    }

    /**
     * 이 테스트를 성공하려면 TODO(4) 를 수정하세요.
     */
    @Test
    public void sayYesComponentOnClass() {
        this.contextRunner
            .run(context -> assertThat(context).hasBean("sayYesComponentOnClass"))
            .run(context -> assertThat(context).hasBean("sayYesComponentOnMissingClass"));
    }

    /**
     * 이 테스트를 성공하려면 TODO(5) 를 수정하세요.
     */
    @Test
    public void sayYesComponentOnProperty() {
        this.contextRunner
            .withPropertyValues("test2=abcd")
            .run(context -> assertThat(context).hasBean("sayYesComponentOnProperty"));
    }

    /**
     * 이 테스트를 성공하려면 TODO(6) 를 수정하세요.
     */
    @Test
    public void sayYesComponentOnResource() {
        this.contextRunner
            .run(context -> assertThat(context).hasBean("sayYesComponentOnResource"));
    }
}
