package bright.zheng.poc.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import bright.zheng.poc.api.model.Student;

@Repository
@Profile({"h2", "mysql"})
public class StudentJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Student findById(long id) {
		Student student = null;
		try {
			student = jdbcTemplate.queryForObject("select * from student where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Student>(Student.class));
		}catch (EmptyResultDataAccessException e) {
			//not found
		}
		
		return student;
	}
}
