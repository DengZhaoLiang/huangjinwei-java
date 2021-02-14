package com.huangjinwei.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

@Component
public class SpringContextUtils implements InitializingBean, ApplicationContextAware {
    private static volatile Environment environment;
    private static volatile ApplicationContext applicationContext;
    private static List<Consumer<ApplicationContext>> contextConsumers = new CopyOnWriteArrayList<>();
    private static List<Consumer<Environment>> environmentConsumers = new CopyOnWriteArrayList<>();
    private static ReadWriteLock contextLock = new ReentrantReadWriteLock();
    private static ReadWriteLock environmentLock = new ReentrantReadWriteLock();
    @Autowired
    private Environment mEnvironment;

    public SpringContextUtils() {
    }

    public static Environment getEnvironment() {
        if (environment == null) {
            throw new RuntimeException("Environment还没有初始化");
        } else {
            return environment;
        }
    }

    public static void getEnvironmentAsync(Consumer<Environment> consumer) {
        if (environment == null) {
            Lock lock = environmentLock.writeLock();

            try {
                lock.lock();
                if (environment == null) {
                    environmentConsumers.add(consumer);
                } else {
                    consumer.accept(environment);
                }
            } finally {
                lock.unlock();
            }
        } else {
            consumer.accept(environment);
        }

    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            throw new RuntimeException("ApplicationContext还没有初始化");
        } else {
            return applicationContext;
        }
    }

    public void setApplicationContext(ApplicationContext context) {
        if (applicationContext == null) {
            applicationContext = context;
            Lock lock = contextLock.readLock();

            try {
                lock.lock();
                contextConsumers.forEach((consumer) -> {
                    consumer.accept(context);
                });
                contextConsumers = null;
                contextLock = null;
            } finally {
                lock.unlock();
            }

        }
    }

    public static void getApplicationContextAsync(Consumer<ApplicationContext> consumer) {
        if (applicationContext == null) {
            Lock lock = contextLock.writeLock();

            try {
                lock.lock();
                if (applicationContext == null) {
                    contextConsumers.add(consumer);
                } else {
                    consumer.accept(applicationContext);
                }
            } finally {
                lock.unlock();
            }
        } else {
            consumer.accept(applicationContext);
        }

    }

    public static <T> T getBean(Class<T> type) {
        return getApplicationContext().getBean(type);
    }

    public static <T> void getBeanAsync(Class<T> type, Consumer<T> consumer) {
        getApplicationContextAsync((context) -> {
            consumer.accept(context.getBean(type));
        });
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public void afterPropertiesSet() throws Exception {
        if (environment == null) {
            environment = this.mEnvironment;
            Lock lock = environmentLock.readLock();

            try {
                lock.lock();
                environmentConsumers.forEach((consumer) -> {
                    consumer.accept(environment);
                });
                environmentConsumers = null;
                environmentLock = null;
            } finally {
                lock.unlock();
            }

        }
    }
}
