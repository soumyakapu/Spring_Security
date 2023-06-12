package spring_security_basic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import spring_security_basic.entity.User;
@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User,Integer> {


    User findByUsername(String username);
}
