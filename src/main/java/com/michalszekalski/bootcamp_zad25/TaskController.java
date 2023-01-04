package com.michalszekalski.bootcamp_zad25;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) Boolean isDone, Model model) {
        List<Task> tasks;

        if (isDone == null) {
            tasks = taskRepository.findAllOrderByDeadlineAsc();
        } else {
            tasks = taskRepository.findByIsDoneOrderByDeadlineAsc(isDone);
        }

        model.addAttribute("filterMode", isDone);
        model.addAttribute("allTasks", tasks);
        model.addAttribute("newTask", new Task());
        model.addAttribute("localDateNow", LocalDate.now());
        return "home";
    }

    @PostMapping("/addTask")
    public String addTask(Task task) {
        task.setIsDone(false);
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping ("/updateIsDone/{id}")
    public String updateIsDone(@PathVariable Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setIsDone(!task.isDone());
            taskRepository.save(task);
        }
        return "redirect:/";
    }
}
