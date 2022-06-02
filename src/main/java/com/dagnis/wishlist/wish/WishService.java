package com.dagnis.wishlist.wish;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public Wish addWish(String wishText) {
        if (wishRepository.existsByWishTextIgnoreCase(wishText)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        try {
            return wishRepository.save(new Wish(wishText));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public Wish updateWish(Long id, String wishText) {
        try {
            Wish wishToUpdate = getWishById(id);
            wishToUpdate.setWishText(wishText);
            return wishRepository.save(wishToUpdate);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    public void deleteWish(Long id) {
        try {
            wishRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Wish getWishById(Long id) {
        Optional<Wish> wish = wishRepository.findById(id);
        if (wish.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return wish.get();
    }

    public List<Wish> getAllWishes() {
        return wishRepository.findAll();
    }

}
