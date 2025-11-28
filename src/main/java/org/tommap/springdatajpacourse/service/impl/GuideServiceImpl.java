package org.tommap.springdatajpacourse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tommap.springdatajpacourse.repository.GuideRepository;
import org.tommap.springdatajpacourse.service.IGuideService;

@Service
@RequiredArgsConstructor
public class GuideServiceImpl implements IGuideService {
    private final GuideRepository guideRepository;

    @Override
    /*
        - by default the spring transaction works in the read-write mode
            -> automatic dirty checking takes place at the transaction commit
     */
    @Transactional
    public void fetchReadWriteGuide() {
        var guide = guideRepository.findByStaffId("2000MO10789")
                .orElseThrow(() -> new RuntimeException("Guide Not Found!!"));
        guide.setSalary(2500);
    }

    @Transactional(readOnly = true)
    public void onlyFetchGuide() {
        var guide = guideRepository.findByStaffId("2000IM10901")
                .orElseThrow(() -> new RuntimeException("Guide Not Found!!"));
        guide.setSalary(5000);
    }
}
