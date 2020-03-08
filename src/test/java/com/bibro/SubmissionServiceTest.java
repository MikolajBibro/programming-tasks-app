package com.bibro;

import com.bibro.domain.task.Language;
import com.bibro.domain.task.Task;
import com.bibro.domain.task.TaskType;
import com.bibro.domain.user.User;
import com.bibro.domain.user.UserCode;
import com.bibro.repository.TaskRepository;
import com.bibro.repository.UserCodeRepository;
import com.bibro.repository.UserRepository;
import com.bibro.service.SubmissionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubmissionServiceTest {


    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCodeRepository userCodeRepository;

    private User user;

    @Before
    public void init() {
        user = new User("username", "email", "password");
        userRepository.save(user);
    }

    @Test
    public void givenCorrectAnswerForStandardTask_whenCallingSubmit_returnTrue() {
        //given correct answer
        Task task = new Task("task", TaskType.Task, "description", "input", "output");
        taskRepository.save(task);

        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"output\");\n" +
                "    }\n" +
                "}\n";

        UserCode userCode = new UserCode(user, code, task, Language.JAVA);
        //when
        boolean answer = submissionService.submit(userCode);

        //then
        Assert.assertTrue(answer);
    }

    @Test
    public void givenCorrectAnswerForStandardTask_whenCallingSubmit_codeSavedInRepository() {
        //given correct answer
        Task task = new Task("task", TaskType.Task, "description", "input", "output");
        taskRepository.save(task);

        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"output\");\n" +
                "    }\n" +
                "}\n";

        UserCode userCode = new UserCode(user, code, task, Language.JAVA);
        //when
        submissionService.submit(userCode);
        //then
        UserCode expected = userCodeRepository.findById(userCode.getId()).orElseThrow(NoSuchElementException::new);

        Assert.assertEquals(userCode.getUser().getUsername(), expected.getUser().getUsername());
        Assert.assertEquals(userCode.getTask().getTask(), expected.getTask().getTask());
        Assert.assertEquals(userCode.getCode(), expected.getCode());
    }
}
