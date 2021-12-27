package com.bobmate.bobmate.service;

import com.bobmate.bobmate.domain.LikeReview;
import com.bobmate.bobmate.domain.Member;
import com.bobmate.bobmate.domain.Review;
import com.bobmate.bobmate.repository.LikeReviewRepository;
import com.bobmate.bobmate.repository.MemberRepository;
import com.bobmate.bobmate.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeReviewService {

    private final LikeReviewRepository likeReviewRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long likeReview(Long memberId, Long reviewId) {
        Member member = memberRepository.findOne(memberId);
        Review review = reviewRepository.findOne(reviewId);

        LikeReview likeReview = LikeReview.createLikeReview(member, review);
        likeReviewRepository.save(likeReview);

        return likeReview.getId();
    }

    @Transactional
    public void unlikeReview(Long memberId, Long reviewId) {
        Member member = memberRepository.findOne(memberId);
        Review review = reviewRepository.findOne(reviewId);

        LikeReview likeReview = likeReviewRepository.findOneByMemberIdAndReviewId(member, review);
        likeReviewRepository.delete(likeReview);
    }
}