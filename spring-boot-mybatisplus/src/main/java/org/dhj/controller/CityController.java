package org.dhj.controller;

import org.dhj.entity.TCity;
import org.dhj.mapper.TCityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

    @Autowired
    private TCityMapper mapper;

    /**
     * asdasasdfasdfasdfasd
     * @param
     * @return
     */
    @GetMapping("getCity")
    public List<TCity> getCity(TCity city){
        System.out.println(city.getPid());
       return mapper.queryListForPid(city);
    }
}
