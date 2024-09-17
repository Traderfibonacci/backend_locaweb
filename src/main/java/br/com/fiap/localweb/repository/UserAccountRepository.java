package br.com.fiap.localweb.repository;

import br.com.fiap.localweb.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
