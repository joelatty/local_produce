package au.com.team2media.localproduce.services;

import au.com.team2media.localproduce.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
