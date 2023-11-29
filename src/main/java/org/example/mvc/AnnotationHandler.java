package org.example.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationHandler {
    private final Class<?> classes;
    private final Method targetMethod;
            ;

    public AnnotationHandler(Class<?> classes, Method targetMethod) {
        this.classes = classes;
        this.targetMethod = targetMethod;
    }

    public String handler(HttpServletRequest request, HttpServletResponse response)throws Exception{
        Constructor<?> declaredConstructor = classes.getDeclaredConstructor();
        Object handler = declaredConstructor.newInstance();

        return (String) targetMethod.invoke(handler, request, response);
    }
}
