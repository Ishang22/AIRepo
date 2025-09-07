package com.dt.mcp.server.feign;

import com.dt.mcp.server.config.FeignMcpS2SClientConfiguration;
import com.dt.platform.cart.ShoppingCart;
import com.dt.platform.catalog.PaginatedProductOffering;
import com.dt.platform.catalog.ProductSpecification;
import com.dt.platform.catalog.SalesContext;
import com.dt.platform.catalog.TariffContext;
import com.dt.platform.common.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${feign.client.config.catalogFeignClient.serviceId:cart}", contextId = "cartFeignClient",
        url = "${feign.client.config.catalogFeignClient.url:}", configuration = FeignMcpS2SClientConfiguration.class)
public interface CartFeignClient {

    String LANGUAGE_HEADER = "content-language";

    @PostMapping("/shoppingCart/v2/shoppingCarts")
    ShoppingCart createCart(@RequestBody ShoppingCart cart);
}
