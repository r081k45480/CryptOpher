package io.robii.cryptoffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.robii.cryptoffer.model.CryptOfferUser;

public interface CryptOfferUserRepository extends JpaRepository<CryptOfferUser, String> {
}
