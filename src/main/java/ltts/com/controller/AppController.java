package ltts.com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import ltts.com.model.Answer;
import ltts.com.model.Codes;
import ltts.com.model.Questions;
import ltts.com.model.User;
import ltts.com.service.QuizService;

@CrossOrigin
@RestController
@RequestMapping("/quiz")
public class AppController {
	@Autowired
	private QuizService service;
	
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)	
	@GetMapping("/welcome")
	public String welcome()
	{
		return "Welcome to first spring web application";
	}	
	
	
	//signup to the quiz website
	@PostMapping("/register")
	public boolean getRegister(@RequestBody User u)
	{		
	
		if(service.adduser(u))
			return true;
		return false;
	}
	
	
	//login verification of the user
	@PostMapping("/login")
	public boolean getUser(
			@RequestBody User user) {
 		if(service.signUp(user.getEmail(),user.getPassword()))
 		   return true;
 		return false;
	}
	
	
	//create quiz in the data base
	@PostMapping("/create-quiz/{codeid}")
	public boolean quizcreate(@PathVariable(name="codeid") int codeid,@RequestBody Codes code) {
		code.setCodeid(codeid);
		System.out.println(code);
		boolean isdone = false;
		Date date=new Date();
		code.setCreatedate(date);
		code.setStatus("enable");
		if(service.addcode(code)) 
		{
			Codes parent=service.findcode(codeid);
			if(parent==null)
				return false;
			Set<Questions> ques=parent.getQuestions();
			for(Questions item:ques) {
				isdone=service.addquestion(item);
			}
		}
		return isdone;
	}
	
	
	//get the quiz questions to give the quiz
	@GetMapping("/give-quiz/{code}")
	public List<Questions> givequiz(@PathVariable(name="code") int code){
		Codes codes = service.findcode(code);
		if(codes==null) {
			return null;
		}
		if(codes.getStatus()=="disable") {
			return null;
		}
		 List<Questions> questions = new ArrayList<Questions>();
		 questions.addAll(codes.getQuestions());
		return questions;
	}
	
	
	//get the the user data in the dashboard
	@GetMapping("/dashboard/{email}")
	public User getuser(@PathVariable(name="email") String email) {
		return service.finduser(email);
	}
	
	//update the user profile
	@PostMapping("/user/update")
	public boolean UpdateUserProfile(@RequestBody User user) {
		if(service.finduser(user.getEmail())!=null) {
			if(service.signUp(user.getEmail(),user.getPassword())) {
				service.adduser(user);
				return true;
			}
			return false;
		}
		return false;
	}
	
	
	//Get the list of the quiz made by the particular email 
	@GetMapping("/dashboard/quizmade/{email}")
	public List<Codes> getquizdata(@PathVariable(name="email") String email) {
		return service.finddata(email);
	}
	
	
	//post the answer of the particular quiz in the answers table
	@PostMapping("/saveanswer")
	public boolean responcedata(@RequestBody Answer answer) {
		return service.putresponce(answer);
	}
	
	
	//get the list of user which are give the quiz 
	@GetMapping("/responce/{code}")
	public List<Answer> getquizresponce(@PathVariable(name="code") int code) {
		return service.quizresponce(code);
	}
	
	
	//get the count of the user which have given the particular quiz
	@GetMapping("/responce/count/{code}")
	public int countofresponce(@PathVariable(name="code") int code) {
		return service.countofresponce(code);
	}
	
	
//	//disable the code 
//	@DeleteMapping("/deletequiz/{code}")
//	public boolean deletequiz(@PathVariable(name="code") int code) {
//		if(service.deletequestions(code))
//		return service.deletequiz(code);
//		return false;
//		
//	}
	
	//enable or disable the quiz
	@PostMapping("/status")
	public boolean changestatus(@RequestBody Codes code) {
		Codes codes = service.findcode(code.getCodeid());
		codes.setStatus(code.getStatus());
		return service.addcode(codes);
	}
}
