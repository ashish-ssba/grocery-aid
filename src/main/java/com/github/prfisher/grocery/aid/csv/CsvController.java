package com.github.prfisher.grocery.aid.csv;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.prfisher.grocery.aid.csv.CsvConverter;
import com.github.prfisher.grocery.aid.models.GroceryList;

@RestController
@RequestMapping("/csv")
public class CsvController {
    @Resource
    CsvConverter converter;

    @PostMapping
    public GroceryList uploadAndDisplayCsv(@RequestParam("file") MultipartFile file) throws Exception {
        final GroceryList list = converter.apply(new String(file.getBytes()));
        
        return list;
    }
}

