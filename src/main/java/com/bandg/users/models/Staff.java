package com.bandg.users.models;

import com.bandg.users.config.FileConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.UUID;

public class Staff {

    @JsonProperty("id")
    private final int id; //0
    @JsonProperty("birthday")
    private final Date birthDay;//1
    @JsonProperty("birthplace")
    private final String birthPlace;//2
    @JsonProperty("Gender")
    private final Gender   sex;//3
    @JsonProperty("cin")
    private final String cin; //ed 4
    @JsonProperty("sitfam")
    private final String sitFam; //5
    @JsonProperty("nationalite")
    private final String nationalite ;//6
    @JsonProperty("dateEmbauche")
    private final  String dateEmbauche;//7
    @JsonProperty("grade")
    private final String grade  ;//8
    @JsonProperty("fucntion")
    private final String function;//9
    @JsonProperty("post")
    private final String post   ; ////10
    @JsonProperty("category")
    private final String category; //11
    @JsonProperty("echelon")
    private final int echelon ;//12
    @JsonProperty("enteffect")
    private final String entEffect ;//13
    @JsonProperty("sectionanalytique")
    private final String sectionAnalytique;//ff 14
    @JsonProperty("regret")
    private final String regimeRetraite;//15 //
    @JsonProperty("affilrecore")
    private final int affilRecore;////16
    @JsonProperty("datederpromo")
    private  final String dateDerPromo; //17
    @JsonProperty("image")
    private  UUID imageid;



    @JsonProperty("image_link")
    private final String imageLink;




    public Staff(
            int id,
            Date birthDay,
            String birthPlace,
            Gender sex,
            String cin,
            String sitFam,
            String nationalite,
            String dateEmbauche,
            String grade,
            String function,
            String post,
            String category,
            int echelon,
            String entEffect,
            String sectionAnalytique,
            String regimeRetraite,
            int affilRecore,
            String dateDerPromo,
                 UUID imageid) {
        this.id = id;
        this.birthDay = birthDay;
        this.birthPlace = birthPlace;
        this.sex = sex;
        this.cin = cin;
        this.sitFam = sitFam;
        this.nationalite = nationalite;
        this.dateEmbauche = dateEmbauche;
        this.grade = grade;
        this.function = function;
        this.post = post;
        this.category = category;
        this.echelon = echelon;
        this.entEffect = entEffect;
        this.sectionAnalytique = sectionAnalytique;
        this.regimeRetraite = regimeRetraite;
        this.affilRecore = affilRecore;
        this.dateDerPromo = dateDerPromo;
        this.imageid = imageid;
        imageLink = "/api/download/"+ this.imageid;
    }
    public Staff(
            @JsonProperty("id") int id,
            @JsonProperty("birthday") Date birthDay,
            @JsonProperty("birthplace") String birthPlace,
            @JsonProperty("Gender") String sex,
            @JsonProperty("cin")String cin,
            @JsonProperty("sitfam") String sitFam,
            @JsonProperty("nationalite")String nationalite,
            @JsonProperty("dateEmbauche") String dateEmbauche,
            @JsonProperty("grade")String grade,
            @JsonProperty("fucntion")  String function,
            @JsonProperty("post")String post,
            @JsonProperty("category")String category,
            @JsonProperty("echelon") int echelon,
            @JsonProperty("enteffect")String entEffect,
            @JsonProperty("sectionanalytique")   String sectionAnalytique,
            @JsonProperty("regret")  String regimeRetraite,
            @JsonProperty("affilrecore")  int affilRecore,
            @JsonProperty("datederpromo")  String dateDerPromo) {
        this.id = id;
        this.birthDay = birthDay;
        this.birthPlace = birthPlace;
        this.sex = sex.trim()
                .equalsIgnoreCase("m") ?
                Gender.MALE:
                Gender.FEMALE;
        this.cin = cin;
        this.sitFam = sitFam;
        this.nationalite = nationalite;
        this.dateEmbauche = dateEmbauche;
        this.grade = grade;
        this.function = function;
        this.post = post;
        this.category = category;
        this.echelon = echelon;
        this.entEffect = entEffect;
        this.sectionAnalytique = sectionAnalytique;
        this.regimeRetraite = regimeRetraite;
        this.affilRecore = affilRecore;
        this.dateDerPromo = dateDerPromo;
     //   System.out.println(fileConfig.getFDef().getId() + "        " + fileConfig.getFDef().getPath());
        if (this.sex.equals(Gender.FEMALE))
            this.imageid = UUID.fromString("53eaf05e-11c6-40c7-b5ea-6337d4583e43");
        else
            this.imageid = UUID.fromString("358053d2-63ec-4c5d-9363-baa8f15c2a9f");
        imageLink = "/api/download/"+ this.imageid;
    }

    public UUID getImageid() {
        return imageid;
    }

    public void setImageid(UUID imageid) {
        this.imageid = imageid;
    }

    public String getImageLink() {
        return imageLink;
    }

    public int getId() {
        return id;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public Gender getSex() {
        return sex;
    }

    public String getCin() {
        return cin;
    }

    public String getSitFam() {
        return sitFam;
    }

    public String getNationalite() {
        return nationalite;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public String getGrade() {
        return grade;
    }

    public String getFunction() {
        return function;
    }


    public String getCategory() {
        return category;
    }

    public int getEchelon() {
        return echelon;
    }

    public String getEntEffect() {
        return entEffect;
    }

    public String getSectionAnalytique() {
        return sectionAnalytique;
    }

    public String getRegimeRetraite() {
        return regimeRetraite;
    }

    public int getAffilRecore() {
        return affilRecore;
    }

    public String getDateDerPromo() {
        return dateDerPromo;
    }

    public UUID getImageId() {
        return imageid;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", birthDay=" + birthDay +
                ", birthPlace='" + birthPlace + '\'' +
                ", sex=" + sex +
                ", cin='" + cin + '\'' +
                ", sitFam='" + sitFam + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", dateEmbauche='" + dateEmbauche + '\'' +
                ", grade='" + grade + '\'' +
                ", function='" + function + '\'' +
                ", post='" + post + '\'' +
                ", category='" + category + '\'' +
                ", echelon=" + echelon +
                ", entEffect='" + entEffect + '\'' +
                ", sectionAnalytique='" + sectionAnalytique + '\'' +
                ", regimeRetraite='" + regimeRetraite + '\'' +
                ", affilRecore=" + affilRecore +
                ", dateDerPromo='" + dateDerPromo + '\'' +
                ", imagePath='" + imageid + '\'' +
                '}';
    }

    public String getPost() {
        return post;
    }
}
