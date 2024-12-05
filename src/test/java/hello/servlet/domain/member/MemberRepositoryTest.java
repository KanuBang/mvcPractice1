package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    void save() {
        Member member = new Member("chanwu", 20);
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        Member member1 = new Member("chanwu", 20);
        Member member2 = new Member("james", 22);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> findMembers = memberRepository.findAll();

        assertThat(findMembers.size()).isEqualTo(2);
        assertThat(findMembers).contains(member1,member2);
    }



}