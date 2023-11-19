package com.juan.vicarius.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.juan.vicarius.entity.ESIndex;
import com.juan.vicarius.request.ESIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private ElasticsearchClient esClient;

    @PostMapping("/create")
    public ResponseEntity<?> createIndex(@RequestBody @Validated ESIndexRequest request) throws IOException {

        ESIndex esIndex = ESIndex.builder()
                .sku(request.sku())
                .name(request.name())
                .price(request.price())
                .build();

        IndexResponse response = esClient.index(i -> i
                .index(esIndex.name())
                .id(esIndex.sku())
                .document(esIndex)
        );

        return ResponseEntity.ok("Index created: " + response.index());
    }
}
