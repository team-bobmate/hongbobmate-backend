package com.bobmate.bobmate.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(columnDefinition = "TEXT")
    private String contents;

    private Double star;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private int likeCount;

    private ReviewStatus reviewStatus;

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getReviews().add(this);
    }

    public void setPlace(Place place) {
        this.place = place;
        place.getReviews().add(this);
    }

    //==생성 메서드==//
    /**
     * 리뷰 생성
     */
    public static Review createReview(Member member, Place place, String contents, Double star) {
        Review review = new Review();
        review.setMember(member);
        review.setPlace(place);
        review.setContents(contents);
        review.setStar(star);
        review.setCreatedDate(LocalDateTime.now());
        review.setLikeCount(0);
        review.setReviewStatus(ReviewStatus.VALID);

        place.addReviewCount();
        place.updateAvgStar();

        return review;
    }

    //==비즈니스 로직==//

    /**
     * 리뷰 삭제
     * 논리적 삭제
     */
    public void delete() {
        reviewStatus = ReviewStatus.DELETED;
        place.subtractReviewCount();
        place.updateAvgStar();
    }
}
