package com.dagnis.wishlist.wish;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wish")
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping("/add")
    public Wish addWish(@RequestParam String wishText) {
        return wishService.addWish(wishText);
    }

    @PutMapping("/update")
    public Wish updateWish(@RequestParam Long id, @RequestParam String wishText) {
        return wishService.updateWish(id, wishText);
    }

    @DeleteMapping("/delete")
    public void deleteWish(@RequestParam Long id) {
        wishService.deleteWish(id);
    }

    @GetMapping("/get")
    public Wish getWish(@RequestParam Long id) {
        return wishService.getWishById(id);
    }

    @GetMapping("/get-all")
    public List<Wish> getAllWishes() {
        return wishService.getAllWishes();
    }
}
