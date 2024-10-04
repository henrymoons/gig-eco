package com.moon.gigeco.service.point;

import org.springframework.stereotype.Service;

import com.moon.gigeco.domain.gigworker.Gigworker;
import com.moon.gigeco.domain.gigworker.GigworkerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointService {
	private final GigworkerRepository profileRepository;

    public void addPoints(Long id, int points) {
    	Gigworker profile = profileRepository.findById(id)
                 .orElseThrow(() -> new IllegalArgumentException("Invalid profile ID"));

    	profile.setPoints(profile.getPoints() + points);
        profileRepository.save(profile);
    }
}
