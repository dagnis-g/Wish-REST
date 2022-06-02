package com.dagnis.wishlist.wish;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {

    boolean existsByWishTextIgnoreCase(String wish);
}
