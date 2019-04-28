package com.idealista.test.models;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ads")
public class Ad {
  @Id
  private Integer id; //Custom Id

  private String description;
  private String typology; //TODO convert to Enum
  private Integer houseSize;
  private Integer gardenSize;
  private List<Integer> pictures;
  private Integer score;

  public Ad (
    Integer id,
    String description,
    String typology,
    Integer houseSize,
    Integer gardenSize,
    List<Integer> pictures  
  ) {
    this.id = id;
    this.description = description;
    this.typology = typology;
    this.houseSize = houseSize;
    this.gardenSize = gardenSize;
    this.pictures = pictures;
    this.score = 0;
  }

  public Integer getId() {
      return this.id;
  }

  public String getDescription() {
      return this.description;
  }

  public String getTypology() {
    return this.typology;
  }

  public Integer getHouseSize() {
    return this.houseSize;
  }

  public Integer getGardenSize() {
    return this.gardenSize;
  }

  public List<Integer> getPictures() {
    return this.pictures;
  }

  public Integer getScore() {
    return this.score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Boolean isComplete() {
    Boolean isComplete = 
      this.pictures != null
      && this.pictures.size() > 0;
    
    if (this.typology != "GARAGE") {
      isComplete = isComplete 
        && this.description != null
        && this.description.length() > 0
        && this.houseSize != null
        && this.houseSize > 0;
      
      if (this.typology == "CHALET") {
        isComplete = isComplete
          && this.gardenSize != null
          && this.gardenSize > 0;
      }
    }

    return isComplete;
  }
}