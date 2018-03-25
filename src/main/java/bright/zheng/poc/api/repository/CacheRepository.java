package bright.zheng.poc.api.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bright.zheng.poc.api.model.Student;

@Repository
@Profile({"redis"})
@EnableRedisRepositories
public interface CacheRepository extends CrudRepository<Student, String> {}
