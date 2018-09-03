package com.github.prfisher.grocery.aid.csv;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.prfisher.grocery.aid.csv.CsvConverter;
import com.github.prfisher.grocery.aid.models.GroceryList;

@Controller
@RequestMapping("/csv")
public class CsvController {
    @Value("${spring.application.name}")
    String appName;

    @Resource
    CsvConverter converter;

    @GetMapping
    public String getUploadCsvPage(final Model model) {
        model.addAttribute("appName", appName);
        return "csv/upload";
    }

    @PostMapping
    public String uploadAndDisplayCsv(@RequestParam("file") MultipartFile file, final Model model) throws Exception {
        model.addAttribute("appName", appName);

        final GroceryList list = converter.apply(new String(file.getBytes()));
        model.addAttribute("list", list);
        
        return "csv/display";
    }
}

