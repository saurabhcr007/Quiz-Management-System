package ltts.com.dao;

import java.util.List;

import ltts.com.model.Answer;
import ltts.com.model.Codes;
import ltts.com.model.Questions;
import ltts.com.model.User;

public interface QuizDAO {
	public boolean adduser(User user);
	public String hashPassword(String password);
	public boolean checkPass(String Password, String hashedPassword);
	public boolean signUp(String email, String password);
	public boolean addcode(Codes code);
	public void addquestion(Questions item);
	public User finduser(String email);
	public List<Codes> finddata(String email);
	public boolean findresponce(Answer answer);
	public String getusername(String email);
	public List<Answer> quizresponce(int code);
	public int countofresponce(int code);
	public Codes findcode(int codeid);
	public boolean deletequiz(int code);
	public boolean deletequestions(int code);
	public boolean changestatus(Codes code);

}
