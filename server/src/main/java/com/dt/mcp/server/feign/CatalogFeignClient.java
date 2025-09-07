package com.dt.mcp.server.feign;

import com.dt.mcp.server.config.FeignMcpS2SClientConfiguration;
import com.dt.platform.catalog.*;
import com.dt.platform.common.Category;
import com.dt.platform.feignclient.config.FeignInternalS2SClientConfiguration;
import org.checkerframework.checker.units.qual.C;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "${feign.client.config.catalogFeignClient.serviceId:catalog}", contextId = "catalogFeignClient",
        url = "${feign.client.config.catalogFeignClient.url:}", configuration = FeignMcpS2SClientConfiguration.class)
public interface CatalogFeignClient {

    String LANGUAGE_HEADER = "content-language";

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/salesCatalog/v1/categories")
    List<Category> getCategory(@RequestHeader(name = LANGUAGE_HEADER) String locale,
            @RequestParam(name = "id") String id, @RequestParam(name = "fields") String fields,
            @RequestParam(name = "checkVisibility") Boolean checkVisibility);

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/salesCatalog/v1/salesOfferings/calculate")
    PaginatedProductOffering    getProduct(@RequestBody SalesContext salesContext);


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/salesCatalog/v1/productSpecifications")
    List<ProductSpecification> getProductSpecifications(@RequestParam(name = "id") String id,
            @RequestParam(name = "categories.id") String categoryId,
            @RequestParam(name = "checkVisibility") Boolean checkVisibility);

    @PostMapping("salesCatalog/v1/tariffOfferings/calculate")
    PaginatedProductOffering getTariffs(@RequestBody TariffContext tariffContext,
            @RequestParam(value = "fields", required = false, defaultValue = "*") String fields);

}
