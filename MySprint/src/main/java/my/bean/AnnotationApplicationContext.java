package my.bean;

import my.annotation.Bean;
import my.annotation.Di;
import my.component.User;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AnnotationApplicationContext implements ApplicationContext{

    private String rootPath;
    // store bean objects
    private Map<Class<?>, Object> beanFactory = new HashMap<>();

    @Override
    public Object getBean(Class<?> c) {
        return beanFactory.get(c);
    }

    public Object getBean(String s) throws ClassNotFoundException {
        return beanFactory.get(Class.forName(s));
    }

    // set scan policy
    public AnnotationApplicationContext(String base) throws ClassNotFoundException {
        String absolutPath = base.replaceAll("\\.", "\\\\");
        // get absolute path of packages
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(absolutPath);
            while(resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String decode = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
                // get parent folder path
                rootPath = decode.substring(1, decode.length() - base.length());
                File file = new File(decode);
                loadBean(file);
            }
        } catch (IOException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        loadDi();
    }

    public void loadBean(File file) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(!file.isDirectory()) {

            String className = file.getAbsolutePath();
            if(!className.endsWith(".class"))
                return;
            // get class name
            className = className.substring(rootPath.length());
            className = className.replace(".class", "");
            className = className.replaceAll("\\\\", ".");
            Class<?> aClass = Class.forName(className);
            if(aClass.getAnnotation(Bean.class)!=null) {
                Class<?> clazz = aClass;
                if(aClass.getInterfaces().length>0)
                    clazz = aClass.getInterfaces()[0];
                beanFactory.put(clazz, aClass.getDeclaredConstructor().newInstance());
                System.out.println("loaded class" + className);
            }
            return;
        }
        for (File listFile : Objects.requireNonNull(file.listFiles())) loadBean(listFile);
    }

    // dependency resolution
    public void loadDi() {
        for (Map.Entry<Class<?>, Object> entry : beanFactory.entrySet()) {
            var obj = entry.getValue();
            // get all member variables
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if(field.getAnnotation(Di.class) != null) {
                    field.setAccessible(true);
                    try {
                        field.set(obj, beanFactory.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            ApplicationContext my = new AnnotationApplicationContext("my");
            User user = (User) my.getBean("my.component.User");
            System.out.println(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
