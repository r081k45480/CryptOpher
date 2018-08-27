package io.robii.cryptoauthservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.robii.cryptoauthservice.model.CryptOfferUser;

public interface CryptOfferUserRepository extends JpaRepository<CryptOfferUser, String> {
}
