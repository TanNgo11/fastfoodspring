package com.fastfood.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fastfood.entity.AccountEntity;

public interface UserRepository extends JpaRepository<AccountEntity, Long> {
	AccountEntity findOneByUserNameAndStatus(String name, int status);

	AccountEntity findOneByOauth2IdAndStatus(String oauth2Id, int status);

	AccountEntity findOneByUserNameAndEmail(String username, String email);

	AccountEntity findTopByUserNameStartingWithOrderByUserNameDesc(String username);

	AccountEntity findOneByUserName(String email);

	@Query("SELECT a FROM AccountEntity a JOIN a.roles r WHERE r.name = :roleName")
	Page<AccountEntity> findByRoleName(@Param("roleName") String roleName, Pageable pageable);

	@Query("SELECT COUNT(a) FROM AccountEntity a JOIN a.roles r WHERE r.name = :roleName")
	long countByRoleName(@Param("roleName") String roleName);

	@Query("SELECT a FROM AccountEntity a JOIN a.roles r WHERE a.id = :accountId AND r.name = :roleName")
	Optional<AccountEntity> findByIdAndRoleName(@Param("accountId") Long accountId, @Param("roleName") String roleName);

}
