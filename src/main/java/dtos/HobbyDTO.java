package dtos;

import entities.Hobby;

import java.util.ArrayList;
import java.util.List;

public class HobbyDTO {
    private long id;
    private String name;
    private String wikiLink;
    private String category;
    private String type;

    public HobbyDTO(String name, String wikiLink, String category, String type) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
    }

    public HobbyDTO(Hobby hobby){
        if (hobby.getId() != null){
            this.id = hobby.getId();
            this.name = hobby.getName();
            this.wikiLink = hobby.getWikilink();
            this.category = hobby.getCategory();
            this.type = hobby.getType();
        }
    }

    public static List<HobbyDTO> getDTOS(List<Hobby> hobbies){
        List<HobbyDTO> hobbyDTOList = new ArrayList<>();
        hobbies.forEach(hobby -> hobbyDTOList.add(new HobbyDTO(hobby)));
        return hobbyDTOList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HobbyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wikiLink='" + wikiLink + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
