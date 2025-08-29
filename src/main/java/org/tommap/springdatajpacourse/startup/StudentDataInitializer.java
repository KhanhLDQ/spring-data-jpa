package org.tommap.springdatajpacourse.startup;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.tommap.springdatajpacourse.entity.Student;
import org.tommap.springdatajpacourse.repository.StudentRepository;

@Component
@RequiredArgsConstructor
public class StudentDataInitializer implements ApplicationRunner {
    private final StudentRepository studentRepository;

    @Override
    //executed after all beans are created & application context is fully setup - however, before the application is completely started
    public void run(ApplicationArguments args) throws Exception {
        if (studentRepository.findByEnrollmentId("2025JPACourse").isEmpty()) {
            Student tom = new Student("Tom", "2025JPACourse");
            studentRepository.save(tom);
        }
    }
}
