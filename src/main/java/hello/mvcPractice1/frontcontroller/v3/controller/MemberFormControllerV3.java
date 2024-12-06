package hello.mvcPractice1.frontcontroller.v3.controller;

import hello.mvcPractice1.frontcontroller.v3.ControllerV3;
import hello.mvcPractice1.frontcontroller.v3.ModelView;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
