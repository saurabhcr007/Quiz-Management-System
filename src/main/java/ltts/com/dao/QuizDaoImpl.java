package ltts.com.dao;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import ltts.com.jpa.AnswersJPARepository;
import ltts.com.jpa.CodesJPARepository;
import ltts.com.jpa.QuestionsJPArepository;
import ltts.com.jpa.UserJPARepository;
import ltts.com.model.Answer;
import ltts.com.model.Codes;
import ltts.com.model.Questions;
import ltts.com.model.User;

@Repository
public class QuizDaoImpl implements QuizDAO {
	@Autowired
	private UserJPARepository use;

	@Autowired
	private CodesJPARepository cod;

	@Autowired
	private QuestionsJPArepository ques;

	@Autowired
	private AnswersJPARepository ans;

	@Override
	public boolean adduser(User user) {
		String encrptedPassword = hashPassword(user.getPassword());
		System.out.println("gpwd:" + encrptedPassword);
		user.setPassword(encrptedPassword);
		if (use.save(user) != null) {
			return true;
		}
		return false;
	}

	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	@Override
	public boolean checkPass(String Password, String hashedPassword) {
		return BCrypt.checkpw(Password, hashedPassword);
	}

	@Override
	public boolean signUp(String email, String password) {
		User getData = use.findByEmail(email);
		if (getData != null) {
			String existingPwd = getData.getPassword();
			System.out.println("epwd:" + existingPwd);
			if (checkPass(password, existingPwd))
				return true;
			return false;
		}
		return false;
	}

	@Override
	public boolean addcode(Codes code) {
		if (cod.save(code) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void addquestion(Questions item) {
		ques.save(item);
	}


	@Override
	public User finduser(String email) {
		return use.findByEmail(email);
	}
	

	@Override
	public List<Codes> finddata(String email) {
		return cod.findByEmail(email);
	}

	@Override
	public boolean findresponce(Answer answer) {
		if (ans.save(answer) != null) {
			return true;
		}
		return false;
	}

	@Override
	public String getusername(String email) {
		return use.getusername(email);
	}

	@Override
	public List<Answer> quizresponce(int code) {
		return ans.findUserByCode(code);
	}

	@Override
	public int countofresponce(int code) {
		return ans.countoftheresponce(code);
	}

	@Override
	public Codes findcode(int codeid) {
		Optional<Codes> getdata = cod.findById(codeid);
		if (getdata.isPresent()) {
			return getdata.get();
		}
		return null;
	}

	@Override
	@Transactional
	public boolean deletequiz(int code) {
		if(cod.deleteByCodeid(code)>0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deletequestions(int code) {
//		if(ques.deleteByCode(code)>0) {
//			return true;
//		}
//		else {
//			return false;
//		}
		return false;
	}

	@Override
	@Transactional
	public boolean changestatus(Codes code) {
		int codeid=code.getCodeid();
		String status=code.getStatus();
		return cod.changestatus(codeid,status);
	}

}