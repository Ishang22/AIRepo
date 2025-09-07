package com.dt.mcp.server.service;

import com.dt.mcp.server.model.HeaderContext;
import com.dt.platform.catalog.PaginatedProductOffering;
import com.dt.platform.catalog.ProductOffering;
import com.dt.platform.catalog.SalesContext;
import com.dt.platform.common.Channel;
import com.dt.mcp.server.feign.CatalogFeignClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Map;

@Service
public class CatalogService {

    private final CatalogFeignClient catalogFeignClient;

    @Autowired
    public CatalogService(CatalogFeignClient catalogFeignClient) {
        this.catalogFeignClient = catalogFeignClient;
    }

    /**
     * Get variant data for given channelId and variantId
     *
     * @param variantId varinatId of the product to be fetched
     * @param channelId  Channel for which the data has to be validated
     * @return PaginatedProductOffering object
     */
    @Tool(name = "GetVariantDataFromSalesOfferingCalculate",
            description = "Get data for single variant Id and for a given channel")
    public PaginatedProductOffering getVariantInfo(String variantId, String channelId) {

        Map<String, String> headers = HeaderContext.get();
        System.out.println("Headers in Tool: " + headers);

        // Use headers if needed, e.g., forwarding
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach(httpHeaders::add);
        }


        SalesContext salesContext = SalesContext.builder()
                .channel(Channel.builder().id(channelId).build())
                .productOfferings(Collections.singletonList(ProductOffering.builder().id(variantId).build()))
                .build();
        PaginatedProductOffering paginatedProductOffering = catalogFeignClient.getProduct(salesContext);
        return paginatedProductOffering;
    }
}
