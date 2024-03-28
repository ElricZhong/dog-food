package com.dogfood.bowl;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 狗碗容器
 * @author zhongfupeng
 */
public class BowlApplicationContext {

    private Class<?> configClass;

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ArrayList<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public BowlApplicationContext(Class<?> configClass) {
        this.configClass = configClass;

        // 扫描类 --> 生成Bean Definition，存到beanDefinitionMap中
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScan = configClass.getAnnotation(ComponentScan.class);

            // com.dogfood.bowlsample
            String path = componentScan.value();

            // 包路径转成为相对路径
            path = path.replace(".", "/");

            ClassLoader classLoader = BowlApplicationContext.class.getClassLoader();

            // 获取classpath下要扫描的目录
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());

            if (file.isDirectory()) {
                // 遍历扫描的目录下的文件
                File[] files = file.listFiles();
                for (File f : files) {
                    String fileName = f.getAbsolutePath();

                    // 说明是class文件，尝试加载类文件
                    if (fileName.endsWith(".class")) {
                        // class文件名转换为类名，项目目录/xx/yy/zz/AA.class => xx.yy.zz.AA
                        String className = fileName.substring(fileName.indexOf(path), fileName.indexOf(".class"));
                        className = className.replace("/", ".");

                        try {
                            Class<?> clazz = classLoader.loadClass(className);
                            if (clazz.isAnnotationPresent(Component.class)) {
                                // TODO BeanPostProcessor

                                // TODO 但这里没有作为单例对象放入容器，现在还会有问题
                                // 先创建BeanPostProcessor对象放入容器
                                if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                    BeanPostProcessor beanPostProcessor = (BeanPostProcessor) clazz.newInstance();
                                    beanPostProcessorList.add(beanPostProcessor);
                                }

                                // 不应该直接创建实例，需要考虑单例多例的情况
                                //Object o = clazz.newInstance();
                                Component component = clazz.getAnnotation(Component.class);

                                String beanName = component.value();
                                if (beanName.equals("")) {
                                    beanName = Introspector.decapitalize(clazz.getSimpleName());
                                }

                                // 创建bd
                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setType(clazz);

                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    Scope scope = clazz.getAnnotation(Scope.class);

                                    beanDefinition.setScope(scope.value());
                                } else {
                                    beanDefinition.setScope("singleton");
                                }

                                // 将db存放到map中
                                beanDefinitionMap.put(beanName, beanDefinition);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        // 创建单例bean，存放到singletonObjects中
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")) {
                // 考虑这种情况：先扫A再扫B，同时A依赖B
                // 扫描A时，先创建了A，后出现依赖注入，再创建了B。
                // 扫描B时，又重复创建了B
                // 所以先判断一下bean是否已创建过
                Object o = singletonObjects.get(beanName);
                if (o != null) {
                    continue;
                }

                o = createBean(beanName, beanDefinition);

                singletonObjects.put(beanName, o);
            }
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();

        try {
            Object instance = clazz.getConstructor().newInstance();

            // 依赖注入
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    field.set(instance, getBean(field.getName()));
                }
            }

            // Aware回调
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware)instance).setBeanName(beanName);
            }

            // BeanPostProcessor 初始化前处理
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(beanName, instance);
            }

            // 初始化
            if (instance instanceof InitializingBean) {
                ((InitializingBean)instance).afterPropertiesSet();
            }

            // BeanPostProcessor 初始化后处理
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(beanName, instance);
            }

            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition == null) {
            throw new NullPointerException();
        } else {
            String scope = beanDefinition.getScope();

            if (scope.equals("singleton")) {
                Object o = singletonObjects.get(beanName);
                if (o == null) {
                    o = createBean(beanName, beanDefinition);
                    singletonObjects.put(beanName, o);
                }
                return o;
            } else {
                // 多例
                return createBean(beanName, beanDefinition);
            }
        }
    }
}
