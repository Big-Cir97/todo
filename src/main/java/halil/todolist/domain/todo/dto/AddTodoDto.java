package halil.todolist.domain.todo.dto;

import halil.todolist.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class AddTodoDto {

    // private Long memberId;

    @Column(nullable = false)
    private String text;

    private String status;
}
