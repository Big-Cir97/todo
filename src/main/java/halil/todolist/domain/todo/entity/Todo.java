package halil.todolist.domain.todo.entity;

import halil.todolist.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    private Member member;
}
