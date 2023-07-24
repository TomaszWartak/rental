package org.example.rentalmysql.entities;

import org.example.rentalmysql.dtos.NewCategorySaveDTO;

public class CategoryBuilder {
    private Long id;
    private String name;
    private String description;

    public CategoryBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Category build() {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);
        return category;
    }

    public Category buildFromDTO( NewCategorySaveDTO newCategorySaveDTO ) {
        Category category = new Category();
        category.setName( newCategorySaveDTO.getName() );
        category.setDescription( newCategorySaveDTO.getDescription() );
        return category;
    }

}
