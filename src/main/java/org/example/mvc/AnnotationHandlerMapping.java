package org.example.mvc;

import org.example.mvc.annotation.RequestMapping;
import org.example.mvc.controller.RequestMethod;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping{
    private final Object[] basePackage;

    private final Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {
        Reflections reflections = new Reflections(basePackage);

        Set<Class<?>> classesWithControllerAnnotation = reflections.getTypesAnnotatedWith(org.example.mvc.annotation.Controller.class, true);

        classesWithControllerAnnotation.forEach(classes ->

                Arrays.stream(classes.getDeclaredMethods()).forEach(declaredMethod -> {
                    RequestMapping requestMappingAnnotation = declaredMethod.getDeclaredAnnotation(RequestMapping.class);

                    Arrays.stream(getRequestMethods(requestMappingAnnotation))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod, requestMappingAnnotation.value()), new AnnotationHandler(classes, declaredMethod)
                            ));

                })
        );
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMappingAnnotation) {
        return requestMappingAnnotation.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}