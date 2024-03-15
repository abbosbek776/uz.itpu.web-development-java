package org.itpu.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "salaries")
public class Salary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="salary_id")
    private Long id;

    @Column
    private Company company;

    @Column
    private Integer level;

    @Column
    private Integer bonusPercentage;

    @Column
    private Double startingSalary;

    @Column
    private Double currentSalary;

    @Column
    private Boolean activeFlag;

    @Column
    private String title;

    public Salary() {
    }

    public Salary(Long id, Company company, Integer level, Integer bonusPercentage, Double startingSalary, Double currentSalary, Boolean activeFlag, String title) {
        this.id = id;
        this.company = company;
        this.level = level;
        this.bonusPercentage = bonusPercentage;
        this.startingSalary = startingSalary;
        this.currentSalary = currentSalary;
        this.activeFlag = activeFlag;
        this.title = title;
    }

    public Salary(Double currentSalary, Boolean activeFlag) {
        this.currentSalary = currentSalary;
        this.activeFlag = activeFlag;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", company=" + company +
                ", level=" + level +
                ", bonusPercentage=" + bonusPercentage +
                ", startingSalary=" + startingSalary +
                ", currentSalary=" + currentSalary +
                ", activeFlag=" + activeFlag +
                ", title='" + title + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(Integer bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }

    public Double getStartingSalary() {
        return startingSalary;
    }

    public void setStartingSalary(Double startingSalary) {
        this.startingSalary = startingSalary;
    }

    public Double getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(Double currentSalary) {
        this.currentSalary = currentSalary;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
