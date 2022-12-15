package halil.todolist.domain.member.service;

import halil.todolist.domain.member.dto.SignUpDto;
import halil.todolist.domain.member.entity.Member;
import halil.todolist.domain.member.exception.session.EmailDuplicate;
import halil.todolist.domain.member.exception.session.LoginUserNotFound;
import halil.todolist.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long signUp(SignUpDto signUpDto) {
        checkEmailDuplicate(signUpDto.getEmail());
        Long id = memberRepository.save(
                        Member.builder()
                                .email(signUpDto.getEmail())
                                .password(passwordEncoder.encode(signUpDto.getPassword()))
                                .build())
                .getId();

        return id;
    }

    @Override
    @Transactional
    public Member login(String email, String password) {
        Member member = checkMember(email, password);
        if (member == null) {
            throw new LoginUserNotFound();
        }

        return member;
    }

    @Override
    public void checkEmailDuplicate(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new EmailDuplicate();
        }
    }

    @Override
    public Member checkMember(String email, String password) {
        // null 일경우 Exception 처리
        memberRepository.findByEmail(email).orElseThrow(() -> new LoginUserNotFound());

        Member member = memberRepository.findByEmail(email).get();
        // member.getPassword().equals()
        if (passwordEncoder.matches(password, member.getPassword())) {
            return member;
        } else {
            return null;
        }
    }
}
