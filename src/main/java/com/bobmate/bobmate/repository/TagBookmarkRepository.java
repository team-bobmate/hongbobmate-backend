package com.bobmate.bobmate.repository;

import com.bobmate.bobmate.domain.Bookmark;
import com.bobmate.bobmate.domain.Member;
import com.bobmate.bobmate.domain.Tag;
import com.bobmate.bobmate.domain.TagBookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagBookmarkRepository {

    private final EntityManager em;

    public void save(TagBookmark tagBookmark) {
        em.persist(tagBookmark);
    }

    public TagBookmark findOne(Long id) {
        return em.find(TagBookmark.class, id);
    }

    public List<TagBookmark> findAll() {
        return em.createQuery("select t from TagBookmark t", TagBookmark.class)
                .getResultList();
    }

    public void delete(TagBookmark tagBookmark) {
        em.remove(tagBookmark);
        em.flush();
        em.clear();
    }

    public TagBookmark findOneByTagIdAndBookmarkIdAndMemberId(Tag tag, Bookmark bookmark, Member member) {
        return em.createQuery("select tb from TagBookmark tb " +
                "where tb.tag = :tag and tb.bookmark = :bookmark and tb.member = :member", TagBookmark.class)
                .setParameter("tag", tag)
                .setParameter("bookmark", bookmark)
                .setParameter("member", member)
                .getResultList().stream().findFirst().orElse(null);
    }

    public List<TagBookmark> findAllByTagIdAndMemberId(Tag tag, Member member) {
        return em.createQuery("select tb from TagBookmark tb " +
                "where tb.tag = :tag and tb.member = :member", TagBookmark.class)
                .setParameter("tag", tag)
                .setParameter("member", member)
                .getResultList();
    }
}