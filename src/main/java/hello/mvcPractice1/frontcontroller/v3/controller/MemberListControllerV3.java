package hello.mvcPractice1.frontcontroller.v3.controller;

import hello.mvcPractice1.domain.member.Member;
import hello.mvcPractice1.domain.member.MemberRepository;
import hello.mvcPractice1.frontcontroller.v3.ControllerV3;
import hello.mvcPractice1.frontcontroller.v3.ModelView;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members", members);
        return modelView;
    }
}
