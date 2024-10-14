package com.khanhisdev.movieservice.dto.Message;

import com.khanhisdev.movieservice.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryMessage {
    private Long id;
    private String name;
    private String thumbnailUrl;
    private String url_key;
    @Override
    public boolean equals(Object object) {
        if(object instanceof Category) {
            Category category = (Category) object;
            return this.name.equals(category.getName());
        }
        return false;
    }
}
