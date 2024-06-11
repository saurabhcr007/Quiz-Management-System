package ltts.com.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ltts.com.model.Codes;

public interface CodesJPARepository extends CrudRepository<Codes, Integer> {

	List<Codes> findByEmail(String email);

	int deleteByCodeid(int codeid);
	
	@Query("update Codes c set c.status=:status where c.codeid=:codeid")
	boolean changestatus(@Param("codeid") int codeid,@Param("status") String status);

}
