package ltts.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ltts.com.dao.QuizDAO;
import ltts.com.model.Answer;
import ltts.com.model.Codes;
import ltts.com.model.Questions;
import ltts.com.model.User;
@Service
public class QuizServiceImpl implements QuizService{
 @Autowired
 private  QuizDAO quiz;
	@Override
	public boolean adduser(User user) {
		return quiz.adduser(user) ;
	}
	@Override
	public boolean signUp(String email, String password) {
		return quiz.signUp(email, password);
	}
	@Override
	public boolean addcode(Codes code) {
		return quiz.addcode(code) ;
	}
	
	@Override
	public boolean addquestion(Questions item) {

		quiz.addquestion(item);
		return true;
	}

	@Override
	public User finduser(String email) {
		return quiz.finduser(email);
	}
	@Override
	public List<Codes> finddata(String email) {
		return quiz.finddata(email);
	}
	@Override
	public boolean putresponce(Answer answer) {
		return quiz.findresponce(answer);
	}
	@Override
	public String getusername(String email) {
		return quiz.getusername(email);
	}
	@Override
	public List<Answer> quizresponce(int code) {
		return quiz.quizresponce(code);
	}
	@Override
	public int countofresponce(int code) {
		return quiz.countofresponce(code);
	}
	@Override
	public Codes findcode(int codeid) {
		return quiz.findcode(codeid) ;
	}
	@Override
	public boolean deletequiz(int code) {
		
		return quiz.deletequiz(code);
	}
	@Override
	public boolean deletequestions(int code) {
		
		return quiz.deletequestions(code);
	}
	@Override
	public boolean changestatus(Codes code) {
		return quiz.changestatus(code);
	}


}
