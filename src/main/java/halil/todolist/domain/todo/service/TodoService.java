package halil.todolist.domain.todo.service;

import halil.todolist.domain.member.entity.Member;
import halil.todolist.domain.member.repository.MemberRepository;
import halil.todolist.domain.todo.dto.AddTodoDto;
import halil.todolist.domain.todo.entity.Todo;
import halil.todolist.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public List<Todo> memberTodos(HttpServletRequest request) {
        return todoRepository.findTodobyMember(getId(request));
    }

    @Transactional
    public Long addTodo(HttpServletRequest request, AddTodoDto addTodoDto) {
        String getEmail = findEmail(request);
        Member member = memberRepository.findMemberByEmail(getEmail);

        Todo todo = todoRepository.save(Todo.builder()
                .member(member)
                .status(addTodoDto.getStatus())
                .text(addTodoDto.getText())
                .build());

        return todo.getId();
    }

    private String findEmail(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object sessionId = session.getAttribute("SessionId");
        // log.info("sessionId = {}", sessionId);
        Member sessionMember = (Member) sessionId;
        return sessionMember.getEmail();
    }

    private Long getId(HttpServletRequest request) {
        String getEmail = findEmail(request);
        return memberRepository.findMemberByEmail(getEmail).getId();
    }
}
