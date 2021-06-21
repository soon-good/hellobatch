package io.study.batch.hellobatch.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = {"name", "price"})
public class Book {
    private String name;
    private Integer price;
    public Book(String name, Integer price){
        this.name = name;
        this.price = price;
    }
}
