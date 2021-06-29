package com.example.shortener.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_url_meta", schema = "shortener")
public class ShortenerEntity {

    @Id
    @Column(name = "SHORTENER_KEY")
    private String shortenerKey;

    @Column(name = "ACTUAL_URL")
    private String actualUrl;

    @Column(name = "COUNTER")
    private int counter;

    public static ShortenerEntity buildShortener(String url) {
        return ShortenerEntity.builder()
                .shortenerKey(randomAlphaNumeric(6))
                .actualUrl(url)
                .counter(0)
                .build();
    }

    public static String randomAlphaNumeric(int count) {
        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
