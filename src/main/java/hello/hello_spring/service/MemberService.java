package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service config에서 빈으로 등록시 컴포넌트 어노테이션 사용 X
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired config에서 빈으로 등록시 컴포넌트 어노테이션 사용 X
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 중복 이름 검즘
     */

    private void validate(Member member){
        // 회원 이름 중복체크 검증
        memberRepository.findByName(member.getName())
                .ifPresent(member1 ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    /**
     * 회원 가입
     */
    public Long join(Member member){
        
        // 회원 이름 중복체크 검증
        validate(member);

        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
