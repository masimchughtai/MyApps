package com.ordertotal.controllers;

import com.ordertotal.model.MarkdownDeals;
import com.ordertotal.model.MarkdownObject;
import com.ordertotal.model.MarkdownRequestEntity;
import com.ordertotal.model.MarkdownResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/markdown")
@ResponseBody
//@RequestBody
public class MarkdownController {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");



    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = {"application/JSON"}, produces = {"application/JSON"})
    public MarkdownResponseEntity createMarkdown(@RequestBody MarkdownRequestEntity markdownRequest){

        Date givenDate;
        givenDate = markdownRequest.effectiveFrom;

        Calendar c = Calendar.getInstance();
        c.setTime(givenDate);
        Date calendarStartDate = c.getTime();
        c.add(Calendar.DAY_OF_MONTH,7);
        c.add(Calendar.SECOND,-1);
        Date calendarEndDate = c.getTime();

        MarkdownDeals markdowns = MarkdownDeals.getInstance();
        MarkdownObject mdObject = new MarkdownObject();

        mdObject.setItem(markdownRequest.getItem());
        mdObject.setMarkdownPrice(markdownRequest.getMarkdownPrice());
        mdObject.setStartDate(calendarStartDate);
        mdObject.setEndDate(calendarEndDate);

        markdowns.markdownDeals.add(mdObject);

        MarkdownResponseEntity responseEntity = new MarkdownResponseEntity();
        responseEntity.setItem(markdownRequest.getItem());
        responseEntity.setMarkdownPrice(markdownRequest.getMarkdownPrice());
        responseEntity.setStartDate(calendarStartDate);
        responseEntity.setEndDate(calendarEndDate);
        return responseEntity;
    }


    @RequestMapping(value = "view-markdown-deals", method = RequestMethod.GET, produces = {"application/JSON"})

    public List<MarkdownObject> viewMarkdownDeals(){
        MarkdownDeals deals = MarkdownDeals.getInstance();

        return deals.markdownDeals;
    }
}
