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
  private List<Integer> pictures;

  public Ad (
    Integer id,
    String description,
    String typology,
    Integer houseSize,
    List<Integer> pictures  
  ) {
    this.id = id;
    this.description = description;
    this.typology = typology;
    this.houseSize = houseSize;
    this.pictures = pictures;
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

  public List<Integer> getPictures() {
    return this.pictures;
  }
}