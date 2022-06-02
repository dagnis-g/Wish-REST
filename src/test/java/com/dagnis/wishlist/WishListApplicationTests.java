package com.dagnis.wishlist;

import com.dagnis.wishlist.wish.Wish;
import com.dagnis.wishlist.wish.WishController;
import com.dagnis.wishlist.wish.WishRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WishListApplicationTests {

    @Autowired
    WishRepository wishRepository;

    @Autowired
    WishController wishController;

    @AfterAll
    void deleteAllWishes() {
        wishRepository.deleteAll();
    }

    @Test
    void shouldInsertWish() {
        String expectedText = "should insert";
        Wish insertedWish = wishController.addWish(expectedText);
        String insertedWishText = insertedWish.getWishText();
        Assertions.assertEquals(expectedText, insertedWishText);
    }

    @Test
    void shouldAutoIncrementId() {
        Wish wish1 = wishController.addWish("wish1");
        Wish wish2 = wishController.addWish("wish2");

        Assertions.assertNotEquals(wish1.getWishId(), wish2.getWishId());
    }

    @Test
    void shouldThrowIfAlreadyInDb() {
        String wishText = "should throw";
        wishController.addWish(wishText);
        Assertions.assertThrows(ResponseStatusException.class,
                () -> wishController.addWish(wishText));
    }

    @Test
    void shouldThrowIfEmptyTextInAddWish() {
        Assertions.assertThrows(ResponseStatusException.class,
                () -> wishController.addWish(""));
    }

    @Test
    void shouldThrowOnNoWishFoundWhenUpdate() {
        Assertions.assertThrows(ResponseStatusException.class,
                () -> wishController.updateWish(12345L, "throw"));
    }

    @Test
    public void shouldThrowIfEmptyTextInUpdate() {
        String wishText = "shouldThrowIfInUpdateEmptyText";
        Wish wish = wishController.addWish(wishText);
        Long wishId = wish.getWishId();

        Assertions.assertThrows(ResponseStatusException.class,
                () -> wishController.updateWish(wishId, ""));
    }
    
    @Test
    void shouldUpdateWish() {
        String wishText = "shouldUpdateWish";
        Wish addedWish = wishController.addWish(wishText);
        Long addedWishId = addedWish.getWishId();

        String expectedUpdatedWishText = "should be updated";
        wishController.updateWish(addedWishId, expectedUpdatedWishText);
        String updatedWishText = wishController.getWish(addedWishId).getWishText();

        Assertions.assertEquals(expectedUpdatedWishText, updatedWishText);
    }

    @Test
    void shouldDeleteWish() {
        String wishText = "Should delete";
        Wish addedWish = wishController.addWish(wishText);
        Long addedWishId = addedWish.getWishId();

        wishController.deleteWish(addedWishId);

        Assertions.assertThrows(ResponseStatusException.class,
                () -> wishController.getWish(addedWishId));
    }

    @Test
    void shouldThrowIfNoWishFoundWhenDelete() {
        Assertions.assertThrows(ResponseStatusException.class,
                () -> wishController.deleteWish(999999L));
    }

    @Test
    void shouldFindById() {
        String wishText = "Should find by id";
        Wish addedWish = wishController.addWish(wishText);
        Long addedWishId = addedWish.getWishId();

        Wish foundWish = wishController.getWish(addedWishId);

        Assertions.assertEquals(addedWish, foundWish);
    }

    @Test
    void shouldThrowIfNoWishFoundById() {
        Assertions.assertThrows(ResponseStatusException.class,
                () -> wishController.getWish(999999L));
    }

    @Test
    void shouldReturnAllWishes() {
        wishRepository.deleteAll();
        Wish wish1 = wishController.addWish("test1");
        Wish wish2 = wishController.addWish("test2");
        Wish wish3 = wishController.addWish("test3");

        List<Wish> wishesAdded = List.of(wish1, wish2, wish3);
        List<Wish> wishesFound = wishController.getAllWishes();

        Assertions.assertEquals(wishesAdded, wishesFound);
    }
}
