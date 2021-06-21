package io.study.batch.hellobatch.config;

import io.study.batch.hellobatch.config.processor.BookItemProcessor;
import io.study.batch.hellobatch.vo.Book;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.ListItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SampleJob1Config {

    @Bean
    public Job sampleJob1(JobBuilderFactory jobBuilderFactory,
                          @Qualifier("sampleStep1") Step sampleStep1){
        return jobBuilderFactory.get("sampleJob1")
                .preventRestart()
                .start(sampleStep1)
                .build();
    }

    @Bean
    public Step sampleStep1(StepBuilderFactory stepBuilderFactory,
                            @Qualifier("sampleBookReader") ListItemReader<Book> sampleBookReader,
                            @Qualifier("sampleBookWriter") ListItemWriter<Book> sampleBookWriter){
        return stepBuilderFactory.get("sampleStep1")
                .<Book, Book>chunk(10)
                .reader(sampleBookReader)
                .processor(new BookItemProcessor())
                .writer(sampleBookWriter)
                .build();
    }

    @Bean
    public ListItemReader<Book> sampleBookReader(){
        List<Book> books = Arrays.asList(
                new Book("A Book on C", 1000),
                new Book("League of Legend", 2000)
        );
        return new ListItemReader<>(books);
    }

    @Bean
    public ListItemWriter<Book> sampleBookWriter(){
        return new ListItemWriter<>();
    }
}
