package org.farpost.farpostapi2.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParsersTest {

    @Autowired
    private CityParser cityParser;

    @Autowired
    private ViewDirParser viewDirParser;

    @Test
    public void testCityParser(){
        cityParser.startParser("/home/makar/IdeaProjects/Farpost/FarpostApi2/src/main/resources/jsons/regions.json");
    }

    @Test
    public void testViewDirParser(){
        viewDirParser.startParser("/home/makar/IdeaProjects/Farpost/FarpostApi2/src/main/resources/jsons/dirs_with_attributes.json");
    }

}
