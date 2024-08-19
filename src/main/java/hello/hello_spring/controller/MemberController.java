package hello.hello_spring.controller;


import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {



    // DI 1번째 생성자 주입 [생성자 주입이 교체 용이함]
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // DI 2번째 필드 주입 [중간에 변경이 어려움]
    /*@Autowired
    private MemberService memberService;*/

    // DI 3번째 Setter 주입 [중간에 변경이 어려움, public 으로 생성해야 함]
    /*private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }*/



    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm.html";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }

}
