package com.moon.gigeco.api.profile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moon.gigeco.domain.gigworker.Gigworker;
import com.moon.gigeco.domain.gigworker.GigworkerRepository;
import com.moon.gigeco.service.profile.ProfileService;



@RestController
@RequestMapping("/api/freelancers")
public class ProfileController {
    private final GigworkerRepository profileRepository;
    private final ProfileService profileService;

    public ProfileController(GigworkerRepository profileRepository, ProfileService profileService) {
        this.profileRepository = profileRepository;
        this.profileService = profileService;
    }

    @GetMapping
    public Page<Gigworker> getProfiles(
            @RequestParam(name = "sortBy", defaultValue = "created_at") String sortBy,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        return profileRepository.findGigworkers(sortBy, PageRequest.of(page, pageSize));
    }

    @PostMapping("/{id}/views")
    public Gigworker updateProfileViews(@PathVariable(name="id") Long id) {
        return profileService.updateViews(id);
    }
    
    @PostMapping
    public Gigworker createProfile(@RequestBody Gigworker profile) {
        return profileService.saveGigworker(profile);
    }
}

