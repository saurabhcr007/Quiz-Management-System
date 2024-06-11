package ltts.com.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ltts.com.model.User;
@Repository
public interface UserJPARepository extends CrudRepository<User, String> {

	User findByEmail(String email);
	
	@Query("select u from User u where u.email=:email")
	String getusername(@Param("email") String email);
}
