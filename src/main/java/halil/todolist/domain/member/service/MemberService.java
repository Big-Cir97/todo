package halil.todolist.domain.member.service;

import halil.todolist.domain.member.dto.SignUpDto;
import halil.todolist.domain.member.entity.Member;
import halil.todolist.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long signUp(SignUpDto signUpDto) {
        Long id = memberRepository.save(
                        Member.builder()
                                .email(signUpDto.getEmail())
                                .password(signUpDto.getPassword())
                                .build())
                .getId();

        return id;
    }
}
