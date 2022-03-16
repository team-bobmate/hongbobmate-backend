package com.bobmate.bobmate.service;

import com.bobmate.bobmate.domain.Tag;
import com.bobmate.bobmate.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;


    /**
     * 태그 생성
     */
    @Transactional
    public Long saveTag(String name) {
        Tag tag = Tag.createTag(name);
        tagRepository.save(tag);
        return tag.getId();
    }


    /**
     * 태그 전체 조회
     */
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }


    /**
     * 태그 삭제
     */
    @Transactional
    public void deleteTag(Long tagId) {
        Tag tag = tagRepository.findOne(tagId);
        tagRepository.delete(tag);
    }

    /**
     * 태그 단일 조회
     */
    public Tag findOne(Long id) {
        return tagRepository.findOne(id);
    }
}
