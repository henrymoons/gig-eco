package com.moon.gigeco.service.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moon.gigeco.domain.gigworker.Gigworker;
import com.moon.gigeco.domain.gigworker.GigworkerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final GigworkerRepository profileRepository;

    @Transactional
    public Gigworker updateViews(Long id) {
        Gigworker profile = profileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profile ID"));

        profile.setViews(profile.getViews() + 1);
        return profileRepository.save(profile);
    }

	public Gigworker saveGigworker(Gigworker profile) {
		// TODO Auto-generated method stub
		return profileRepository.save(profile);
	}
}

