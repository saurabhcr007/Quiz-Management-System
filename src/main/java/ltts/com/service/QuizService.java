package ltts.com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ltts.com.model.Answer;
import ltts.com.model.Codes;
import ltts.com.model.Questions;
import ltts.com.model.User;

@Service
public interface QuizService {
	public boolean adduser(User user) ;
	public boolean signUp(String email, String password);
	public boolean addcode(Codes code);
	public boolean addquestion(Questions item);
	public User finduser(String email);
	public List<Codes> finddata(String email);
	public boolean putresponce(Answer answer);
	public String getusername(String email);
	public List<Answer> quizresponce(int code);
	public int countofresponce(int code);
	public Codes findcode(int codeid) ;
	public boolean deletequiz(int code);
	public boolean deletequestions(int code);
	public boolean changestatus(Codes code);

}
