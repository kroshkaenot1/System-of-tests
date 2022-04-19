package com.rgr.system_of_tests.service;

import com.rgr.system_of_tests.repo.*;
import com.rgr.system_of_tests.repo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TestService {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserService usersService;
    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TestsRepository testsRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    public Iterable<Test> getAllTests(){
        Iterable<Test> tests = testsRepository.findAll();
        return tests;
    }
    public Test getTestById(long id){
        Test test = testsRepository.findId(id);
        return test;
    }
    public void EditTest(long id,Map<String, String> form,MultipartFile img1,MultipartFile img2,
                         MultipartFile img3,MultipartFile img4,MultipartFile img5,
                         MultipartFile img6,MultipartFile img7,MultipartFile img8,
                         MultipartFile img9,MultipartFile img10) throws IOException {
        Test test = testsRepository.findId(id);
        List<Question> q= questionRepository.findByTestId(test.getId());
        questionRepository.deleteAll(q);
        int q_count = 1;
        int a_count = 1;
        Long last_id_q = null;
        int ball = 0;
        for(String key : form.keySet()){
            if(key.equals("isPrivate")){
                if(form.get(key).equals("private")){
                    test.setPrivate(true);
                }
            }if(key.equals("names[]")){
                Long userId = Long.parseLong(form.get(key));
                Invitation inv = new Invitation(test.getId(),userId);
                invitationRepository.save(inv);
                User user = usersRepository.findId(userId);
                String messageForEmail = String.format(
                        "Доброго времени суток, %s! Вас пригласили пройти тестирование: http://localhost:8080/test/%s",
                        user.getFirstname(),
                        test.getId()
                );
                mailSender.send(user.getUsername(),"Приглашение",messageForEmail);
            }
            if(key.equals("a"+q_count+a_count)){
                ball = Integer.parseInt(form.get("b"+q_count+a_count));
                Answer answer = new Answer(last_id_q,form.get(key),ball);
                answerRepository.save(answer);
                a_count++;
                if(a_count==3){
                    if(!form.containsKey("a"+q_count+a_count)){q_count++; a_count=1;}
                }
                if(a_count==4){a_count=1;q_count++;}
            }
            if(key.equals("q"+q_count)){
                String resultFilename = null;
                if(q_count==1){
                    if(img1.getSize()!=0){
                        resultFilename = files(img1);
                    }
                }if(q_count==2){
                    if(img2.getSize()!=0){
                        resultFilename = files(img2);
                    }
                }if(q_count==3){
                    if(img3.getSize()!=0){
                        resultFilename = files(img3);
                    }
                }if(q_count==4){
                    if(img4.getSize()!=0){
                        resultFilename = files(img4);
                    }
                }if(q_count==5){
                    if(img5.getSize()!=0){
                        resultFilename = files(img5);
                    }
                }if(q_count==6){
                    if(img6.getSize()!=0){
                        resultFilename = files(img6);
                    }
                }if(q_count==7){
                    if(img7.getSize()!=0){
                        resultFilename = files(img7);
                    }
                }if(q_count==8){
                    if(img8.getSize()!=0){
                        resultFilename = files(img8);
                    }
                }if(q_count==9){
                    if(img9.getSize()!=0){
                        resultFilename = files(img9);
                    }
                }if(q_count==10){
                    if(img10.getSize()!=0){
                        resultFilename = files(img10);
                    }
                }
                Question question = new Question(test.getId(),form.get(key));
                if(form.get("img"+q_count)!=null){
                    question.setFilename(form.get("img"+q_count));
                }
                question.setFilename(resultFilename);
                questionRepository.save(question);
                last_id_q =question.getId();
            }
        }
    }
    public void deleteTest(long id){
        Test test = testsRepository.findId(id);
        testsRepository.delete(test);
    }
    public void addTest(Map<String, String> form,MultipartFile img1,MultipartFile img2,
                        MultipartFile img3,MultipartFile img4,MultipartFile img5,
                        MultipartFile img6,MultipartFile img7,MultipartFile img8,
                        MultipartFile img9,MultipartFile img10) throws IOException {
        Test test = new Test(form.get("title"),form.get("description"),false);
        testsRepository.save(test);
        int q_count = 1;
        int a_count = 1;
        Long last_id_q = null;
        int ball = 0;
        for(String key : form.keySet()){
            if(key.equals("isPrivate")){
                if(form.get(key).equals("private")){
                    test.setPrivate(true);
                }
            }
            if(key.equals("names[]")){
                Long userId = Long.parseLong(form.get(key));
                Invitation inv = new Invitation(test.getId(),userId);
                invitationRepository.save(inv);
                User user = usersRepository.findId(userId);
                String messageForEmail = String.format(
                        "Доброго времени суток, %s! Вас пригласили пройти тестирование: http://localhost:8080/test/%s",
                        user.getFirstname(),
                        test.getId()
                );
                mailSender.send(user.getUsername(),"Приглашение",messageForEmail);
            }
            if(key.equals("a"+q_count+a_count)){
                ball = Integer.parseInt(form.get("b"+q_count+a_count));
                Answer answer = new Answer(last_id_q,form.get(key),ball);
                answerRepository.save(answer);
                a_count++;
                if(a_count==3){
                    if(!form.containsKey("a"+q_count+a_count)){q_count++; a_count=1;}
                }
                if(a_count==4){a_count=1;q_count++;}
            }
            if(key.equals("q"+q_count)){
                String resultFilename = null;
                if(q_count==1){
                    if(img1.getSize()!=0){
                        resultFilename = files(img1);
                    }
                }if(q_count==2){
                    if(img2.getSize()!=0){
                        resultFilename = files(img2);
                    }
                }if(q_count==3){
                    if(img3.getSize()!=0){
                        resultFilename = files(img3);
                    }
                }if(q_count==4){
                    if(img4.getSize()!=0){
                        resultFilename = files(img4);
                    }
                }if(q_count==5){
                    if(img5.getSize()!=0){
                        resultFilename = files(img5);
                    }
                }if(q_count==6){
                    if(img6.getSize()!=0){
                        resultFilename = files(img6);
                    }
                }if(q_count==7){
                    if(img7.getSize()!=0){
                        resultFilename = files(img7);
                    }
                }if(q_count==8){
                    if(img8.getSize()!=0){
                        resultFilename = files(img8);
                    }
                }if(q_count==9){
                    if(img9.getSize()!=0){
                        resultFilename = files(img9);
                    }
                }if(q_count==10){
                    if(img10.getSize()!=0){
                        resultFilename = files(img10);
                    }
                }
                Question question = new Question(test.getId(),form.get(key));
                question.setFilename(resultFilename);
                questionRepository.save(question);
                last_id_q =question.getId();
            }
        }
    }
    public ArrayList<Test> testsSearch(String date,String search){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date1 = null;
        try {
            date1 = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArrayList<Test> resTests = testsRepository.findByDate(date1);
        ArrayList<Test> TestsForModel = new ArrayList<>();
        for(Test t:resTests){
            if(t.getTitle().toLowerCase().contains(search.toLowerCase()) || t.getDescription().toLowerCase().contains(search.toLowerCase())){
                TestsForModel.add(t);
            }
        }
        return TestsForModel;
    }
    public ArrayList<QuestionModel> testViewById(long id){
        Test test = testsRepository.findId(id);
        User user = usersRepository.findByUsername(usersService.getCurrentUsername());
        if(user.ifRole("ADMIN")==false&&user.ifRole("TESTER")==false){
            if(test.getPrivate()){
                Invitation invitation = invitationRepository.findId(test.getId(),user.getId());
                if(invitation==null){
                    return null;
                }
            }
        }
        List<Question> questions = questionRepository.findByTestId(id);
        ArrayList<QuestionModel> qm = new ArrayList<>();
        for(Question q : questions){
            List<Answer>  answers = answerRepository.findByQuestionId(q.getId());
            try{
                QuestionModel questionModel = new QuestionModel(q.getQuestion_text(),answers.get(0).getAnswer(),answers.get(1).getAnswer(),answers.get(2).getAnswer(),
                        answers.get(0).getId(),answers.get(1).getId(),answers.get(2).getId(),q.getId(),q.getFilename());
                qm.add(questionModel);
            }catch (IndexOutOfBoundsException e){
                QuestionModel questionModel = new QuestionModel(q.getQuestion_text(),answers.get(0).getAnswer(),answers.get(1).getAnswer(),null,
                        answers.get(0).getId(),answers.get(1).getId(),null,q.getId(),q.getFilename());
                qm.add(questionModel);
            }
        }
        return qm;
    }
    public String getTestResult(long id,Map<String, String> form){

        int result=0;
        Test test = testsRepository.findId(id);
        int max=0;
        String name = "";
        String email = "";
        List<Answer> answers = answerRepository.findAllByTest(id);
        for(Answer a : answers){
            max+=a.getScore();
        }
        for(String key: form.keySet()){
            if(key.equals("userId")){
                email = form.get(key);
                User user = usersRepository.findByName(email);
                name = user.getFirstname();
                continue;}

            Long b = Long.parseLong(form.get(key));
            Answer answer = answerRepository.findBy_Id(b);
            result+=answer.getScore();
        }
        String message = String.format(
                "%s, вы набрали %s баллов из %s возможных",
                name,
                result,
                max
        );
        String messageForEmail = String.format(
                "%s.\n"+
                        "%s, вы набрали %s баллов из %s возможных",
                test.getTitle(),
                name,
                result,
                max

        );
        mailSender.send(email,"Результат тестированя",messageForEmail);
        return message;

    }

    public String files(MultipartFile file) throws IOException {
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();
        file.transferTo(new File(uploadPath + "/" + resultFilename));
        return resultFilename;
    }
}
