package ltts.com.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ltts.com.model.Answer;

public interface AnswersJPARepository extends CrudRepository<Answer, Integer> {

	boolean findByEmail(String email);
	
	@Query("select a from Answer a where a.email=:email")
	List<Answer> findUserByEmail(@Param("email")String email);
	
	@Query("select count(a) from Answer a where a.code=:code")
	int countoftheresponce(@Param("code")int code);

	List<Answer> findUserByCode(int code);
}
