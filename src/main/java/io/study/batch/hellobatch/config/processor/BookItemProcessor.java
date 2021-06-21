package io.study.batch.hellobatch.config.processor;

import io.study.batch.hellobatch.vo.Book;
import org.springframework.batch.item.ItemProcessor;

public class BookItemProcessor implements ItemProcessor<Book, Book> {
    @Override
    public Book process(Book item) throws Exception {
        return item;
    }
}
