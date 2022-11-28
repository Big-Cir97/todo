package halil.todolist.domain.todo.controller;

import halil.todolist.domain.member.dto.LoginDto;
import halil.todolist.domain.member.entity.Member;
import halil.todolist.domain.member.login.session.SessionService;
import halil.todolist.domain.todo.dto.AddTodoDto;
import halil.todolist.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public String todos(Model model, HttpServletRequest request) {
        model.addAttribute("todos", todoService.memberTodos(request));
        return "todos";
    }

    /**
     * Todo 추가
     * @param request
     * @param addTodoDto
     * @param model
     * @return
     */
    @PostMapping("/todos/add")
    public String addTodo(HttpServletRequest request,
                          @ModelAttribute AddTodoDto addTodoDto,
                          Model model) {

        model.addAttribute("addTodoDto", todoService.addTodo(request, addTodoDto));
        return "redirect:/todos";
    }

    @PostMapping("/todoUpdate/{id}")
    public String updateTodo(@PathVariable Long id) {
        todoService.updateTodo(id);
        return "redirect:/todos";
    }

    @PostMapping("/todoDelete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }
}
