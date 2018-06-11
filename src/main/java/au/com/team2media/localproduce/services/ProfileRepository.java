package au.com.team2media.localproduce.services;

import au.com.team2media.localproduce.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
