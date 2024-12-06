package hello.mvcPractice1.frontcontroller.v3;

import hello.mvcPractice1.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.mvcPractice1.frontcontroller.v3.controller.MemberListControllerV3;
import hello.mvcPractice1.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        ControllerV3 controllerV3 = controllerMap.get(requestURI); // 1. 컨트롤러 조회
        if(controllerV3 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request); // 파라미터 정보 생성
        ModelView modelView = controllerV3.process(paramMap);
        // 2. 파라미터 정보를 컨트롤러에 넘겨주고 컨트롤러는 그 정보로 객체 생성 후 저장소에 저장하고 ModelView를 넘겨줌

        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);
        // 3. viewResolver를 호출하여 MyView반환
        view.render(modelView.getModel(),request,response);

    }

    private Map<String,String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
