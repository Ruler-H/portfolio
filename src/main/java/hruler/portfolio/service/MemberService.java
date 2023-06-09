package hruler.portfolio.service;

import hruler.portfolio.domain.Member;
import hruler.portfolio.dto.MemberRegisterDto;
import hruler.portfolio.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final EntityManager em;

    /**
     * Join Member
     * @param member
     * @return Joined Member Id
     */
    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * Update Member Information
     * @param memberId
     * @param form
     */
    @Transactional
    public void update(Long memberId, MemberRegisterDto form, String memberName) {
        Member findMember = findOne(memberId);
        findMember.updateInfo(form, memberName);
    }

    public boolean validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        return !findMembers.isEmpty();
    }

    /**
     * Search Total Member
     * @return Total Member Entity as a Collection
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * Search Member By MemberId
     * @param memberId
     * @return Searched Member Entity
     */
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
