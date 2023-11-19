package com.juan.vicarius.controller;

import com.juan.vicarius.entity.ESDocument;
import com.juan.vicarius.request.DocumentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @PostMapping("/create")
    public ResponseEntity<?> createDocument(@RequestBody @Validated DocumentRequest request) {
        ESDocument document = elasticsearchOperations.save(ESDocument.builder()
                .id(request.getId())
                .name(request.getName())
                .summary(request.getSummary())
                .price(request.getPrice())
                .build());

        return ResponseEntity.ok(document.getId());
    }

    @GetMapping("/document/{id}")
    public ResponseEntity<?> getDocumentById(@PathVariable String id) {
        Optional<ESDocument> document = Optional.ofNullable(elasticsearchOperations.get(id, ESDocument.class));

        if (document.isEmpty()) {
            return new ResponseEntity<>(document, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(document.get(), HttpStatus.OK);
    }
}
