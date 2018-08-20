package bright.zheng.poc.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import bright.zheng.poc.api.model.Student;

import java.util.List;

@Repository
@Profile({"h2", "mysql"})
public class StudentJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Student findById(long id) {
		Student student = null;
		try {
			student = jdbcTemplate.queryForObject("select * from student where id=?",
							new Object[] { id },
							new BeanPropertyRowMapper<Student>(Student.class));
		}catch (EmptyResultDataAccessException e) {
			//not found
		}
		
		return student;
	}

	public List<Student> findAll() {
		List<Student> students = null;
		try {
			students = jdbcTemplate.query("select * from student",
							new BeanPropertyRowMapper<Student>(Student.class));
		}catch (EmptyResultDataAccessException e) {
			//not found
		}

		return students;
	}
}
