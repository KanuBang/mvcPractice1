package hello.mvcPractice1.frontcontroller.v5.adapter;

import hello.mvcPractice1.frontcontroller.v3.ControllerV3;
import hello.mvcPractice1.frontcontroller.v3.ModelView;
import hello.mvcPractice1.frontcontroller.v4.ControllerV4;
import hello.mvcPractice1.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV4;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controllerV4 = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controllerV4.process(paramMap, model);

        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);
        return modelView;
    }


    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }
}
