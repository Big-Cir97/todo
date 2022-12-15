package halil.todolist.domain.member.service;

import halil.todolist.domain.member.dto.SignUpDto;
import halil.todolist.domain.member.entity.Member;

public interface MemberService {
    public Long signUp(SignUpDto signUpDto);
    public Member login(String email, String password);
    public Member checkMember(String email, String password);
    public void checkEmailDuplicate(String email);
}
