package br.com.devtech.api.repositories;

import br.com.devtech.api.models.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

  @Query("SELECT u FROM UsersEntity u WHERE u.userName = :userName")
  UsersEntity findByUsername(@Param("userName") String userName);
}
