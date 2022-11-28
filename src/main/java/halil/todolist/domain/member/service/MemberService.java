package halil.todolist.domain.member.service;

import halil.todolist.domain.member.dto.SignUpDto;
import halil.todolist.domain.member.entity.Member;
import halil.todolist.domain.member.exception.session.EmailDuplicate;
import halil.todolist.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signUp(SignUpDto signUpDto) {
        checkEmailDuplicate(signUpDto.getEmail());
        Long id = memberRepository.save(
                        Member.builder()
                                .email(signUpDto.getEmail())
                                .password(signUpDto.getPassword())
                                .build())
                .getId();

        return id;
    }

    private void checkEmailDuplicate(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new EmailDuplicate();
        }
    }
}
