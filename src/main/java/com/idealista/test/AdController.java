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
import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {
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
  public Ad createAd(@Valid @RequestBody Ad ad) {
    repository.save(ad);
    return ad;
  }
}
