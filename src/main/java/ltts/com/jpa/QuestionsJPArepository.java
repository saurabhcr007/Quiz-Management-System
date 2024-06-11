package ltts.com.jpa;


import org.springframework.data.repository.CrudRepository;

import ltts.com.model.Questions;


public interface QuestionsJPArepository extends CrudRepository<Questions, Integer> {

//	@Query("delete from Questions q where q.code=:code")	
//	int deleteByCode(@Param("code") int code);


}
