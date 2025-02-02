package hello.demo.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class SingletonWithPrototypeTest1 {

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean = ac.getBean(ClientBean.class);
        int count1 = clientBean.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    @Component
    @Scope("singleton")
    static class ClientBean{

        @Autowired
        private Provider<PrototypeBean> provider;

//        @Autowired
//        private ApplicationContext ac;
//        private final PrototypeBean prototypeBean;
//
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Component
    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy" + this);
        }

    }
}
