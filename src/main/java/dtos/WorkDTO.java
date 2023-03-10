package dtos;

import entities.Person;
import entities.Work;

import java.util.ArrayList;
import java.util.List;

public class WorkDTO {
    private long id;
    private String title;
    private String description;


    public WorkDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    private WorkDTO(Work work){
        if(work.getId() != null){
            this.id = work.getId();
            this.title = work.getTitle();
            this.description = work.getDescription();
        }
    }

    public static List<WorkDTO> getDtos(List<Work> workList){
        List<WorkDTO> workDTOS = new ArrayList();
        workList.forEach(work->workDTOS.add(new WorkDTO(work)));
        return workDTOS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WorkDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
