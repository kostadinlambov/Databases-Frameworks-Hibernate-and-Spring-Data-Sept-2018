package alararestaurant.domain.dtos;

import alararestaurant.domain.entities.Position;
import com.google.gson.annotations.Expose;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeImportDto {
    @Expose
    private String name;
    @Expose
    private Integer age;
    @Expose
    private String position;

    public EmployeeImportDto() {
    }

    @NotNull
    @Size(min = 3, max = 30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Min(value = 15)
    @Max(value = 80)
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @NotNull
    @Size(min = 3, max = 30)
    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
