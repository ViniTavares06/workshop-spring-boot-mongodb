package com.workshop.workshopmongo.resources;

import com.workshop.workshopmongo.domain.Post;
import com.workshop.workshopmongo.resources.util.URL;
import com.workshop.workshopmongo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body((obj));
    }

    public ResponseEntity<List<Post>> findByTitle(
            @RequestParam(value = "text", defaultValue = "") String text
    ) {
        try {
            String decodedText = URL.decodeParam(text);
            List<Post> list = service.findByTitle(decodedText);
            return ResponseEntity.ok().body(list);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate
    ) throws UnsupportedEncodingException {

        text = URLDecoder.decode(text, StandardCharsets.UTF_8);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date min;
        try {
            min = minDate.isEmpty() ? new Date(0L) : sdf.parse(minDate);
        } catch (ParseException e) {
            min = new Date(0L);
        }

        Date max;
        try {
            max = maxDate.isEmpty() ? new Date() : sdf.parse(maxDate);
        } catch (ParseException e) {
            max = new Date();
        }

        List<Post> list = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}

