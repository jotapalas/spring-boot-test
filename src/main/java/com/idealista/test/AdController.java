package com.idealista.test;

import com.idealista.test.models.Ad;
import com.idealista.test.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {
  public static final String[] IMPORTANT_WORDS = {
    "luminoso",
    "nuevo",
    "céntrico",
    "reformado",
    "ático"
  };

  @Autowired
  private AdRepository repository;
  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<Ad> getAllAds() {
    return repository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Ad getAdById(@PathVariable("id") Integer id) {
    return repository.findById(id);
  }
  
  @RequestMapping(value = "", method = RequestMethod.POST)
  public List<Ad> createAd(@Valid @RequestBody List<Ad> adsList) {
    repository.saveAll(adsList);
    return adsList;
  }

  @RequestMapping(value = "/{id}/setScore", method = RequestMethod.PUT)
  public Ad setScore(@PathVariable("id") Integer id) {
    Ad ad = getAdById(id);
    Integer score = 0;

    //Que el anuncio no tenga foto resta 10 puntos
    if (ad.getPictures().size() == 0) {
      score -= 10;
    }

    //Cada foto que tenga el anuncio proporciona 20 puntos si es una foto HD o 10 si no lo es


    //Que tenga descripción aporta 5 puntos
    String adDescription = ad.getDescription();
    if (adDescription != null  && adDescription.length() > 0) {
      score += 5;
      String adTypology = ad.getTypology();
      List<String> adWords = Arrays.asList(adDescription.toLowerCase().split(" "));
      Integer wordCount = adWords.size();
      if (adTypology == "FLAT" && wordCount >= 20) {
        score += wordCount >= 50 ? 30 : 10;
      }
      if (adTypology == "CHALET" && wordCount >= 50) {
        score += 20;
      }
      
      for (String s : IMPORTANT_WORDS) {
        if (adWords.contains(s)){
          score += 5;
        }
      }
    }
    if (ad.isComplete()) {
      score += 40;
    }
    ad.setScore(score);
    repository.save(ad);
    return ad;
  }
}
