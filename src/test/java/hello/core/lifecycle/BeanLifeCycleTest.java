package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac =
            new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        //step1 : NetworkClient url = null 나온다. 왜? url set 하지 않아서.
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            //setUrl 을 나중에 해서 생성자에서 set하지 않은것이 문제
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
