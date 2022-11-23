package halil.todolist.domain.todo.controller;

import halil.todolist.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
}
