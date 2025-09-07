package com.dt.mcp.server.feign;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.dt.mcp.server.config.SyliusMcpFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import com.dt.mcp.server.model.SyliusAttribute;

@FeignClient(
        contextId = "syliusapiservice",
        name = "syliusApiService",
        url = "${feign.client.config.syliusapiservice.url:}",
        configuration = SyliusMcpFeignConfiguration.class
)
public interface SyliusMcpApiService {

    //https://eshop-sales-catalog-ui.stagev4.eshop.yo-digital.com/api/eshop/salesCatalogAdmin/v1/attributes/create
    @PostMapping("api/eshop/salesCatalogAdmin/v1/attributes/create")
    public ResponseEntity<SyliusAttribute> createUpdateAttribute(@RequestBody SyliusAttribute catalog);


}
