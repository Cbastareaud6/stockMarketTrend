package com.CathyB.stockMarketTrend.Repository;


import com.CathyB.stockMarketTrend.Model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserName(String username);
}
