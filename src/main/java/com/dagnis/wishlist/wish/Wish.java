package com.dagnis.wishlist.wish;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Wish {

    @Id
    @Column(name = "wish_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;

    @Column(name = "wish")
    @NotBlank
    private String wishText;

    public Wish(String wishText) {
        this.wishText = wishText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wish wish1 = (Wish) o;

        return wishText != null ? wishText.equals(wish1.wishText) : wish1.wishText == null;
    }

    @Override
    public int hashCode() {
        return wishText != null ? wishText.hashCode() : 0;
    }
}
