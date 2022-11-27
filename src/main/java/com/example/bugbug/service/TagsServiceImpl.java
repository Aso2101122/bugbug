package com.example.bugbug.service;

import com.example.bugbug.entity.RecipeTag;
import com.example.bugbug.entity.Tag;
import com.example.bugbug.repository.TagsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TagsServiceImpl implements TagsService {

    private TagsRepository tagsRepository;


    /**
     * レシピタグのリストを受け取りタグ情報をリストで返す
     * @param recipeTags
     * @return
     */
    @Override
    public List<Tag> getTags(List<RecipeTag> recipeTags) {
        List<Tag> tags = new ArrayList<>();
        // タグを取得
        recipeTags.forEach(recipeTag -> tags.add(tagsRepository.findById(recipeTag.getTagId()).get()));
        return tags;
    }
}
