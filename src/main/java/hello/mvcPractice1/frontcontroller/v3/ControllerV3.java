package hello.mvcPractice1.frontcontroller.v3;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String,String> paramMap);
}
