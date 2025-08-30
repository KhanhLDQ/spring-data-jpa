package org.tommap.springdatajpacourse.startup;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.tommap.springdatajpacourse.entity.Student;
import org.tommap.springdatajpacourse.repository.StudentRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class StudentDataInitializer implements ApplicationRunner {
    private final StudentRepository studentRepository;

    @Override
    //executed after all beans are created & application context is fully setup - however, before the application is completely started
    public void run(ApplicationArguments args) throws Exception {
        if (studentRepository.findByEnrollmentId("2025JPACourse").isEmpty()) {
            Student tom = new Student("Tom", "2025JPACourse");
            Student savedTom = studentRepository.save(tom); //persist a new object - after save() tom & savedTom point to the same object
            log.debug("[Insert] Do tom & savedTom point to the same object: {}", tom == savedTom);

            savedTom.setName("Tom Coder");
            Student updatedTom = studentRepository.save(savedTom); //update an existing object - after save() savedTom & updatedTom are different
            log.debug("[Update] Do tom & savedTom point to the same object: {}", savedTom == updatedTom);

            studentRepository.deleteById(updatedTom.getId());
        }
    }
}
