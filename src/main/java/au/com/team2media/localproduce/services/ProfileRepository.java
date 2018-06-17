package au.com.team2media.localproduce.services;

import au.com.team2media.localproduce.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findByMemberMemberId(Long memberId);
}

