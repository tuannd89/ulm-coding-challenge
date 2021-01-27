package com.unlimint.task;

import com.unlimint.task.helper.FileProcessHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ProcessFileApplication implements CommandLineRunner {

    @Autowired
    private FileProcessHelper fileProcessHelper;

    public static void main(String[] args) {
        SpringApplication.run(ProcessFileApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // process file in parallel
        Arrays.stream(args).parallel().forEach(pathname -> fileProcessHelper.processFile(pathname));
    }
}
