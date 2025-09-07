package com.dt.mcp.server.service;

import com.dt.mcp.server.Cart;
import com.dt.mcp.server.feign.CartFeignClient;
import com.dt.mcp.server.feign.CatalogFeignClient;

import com.dt.mcp.server.feign.SyliusMcpApiService;
import com.dt.mcp.server.model.CartLineItem;
import com.dt.mcp.server.model.SyliusAttribute;
import com.dt.platform.cart.CartItem;
import com.dt.platform.cart.ShoppingCart;
import com.dt.platform.cart.constants.Action;
import com.dt.platform.catalog.PaginatedProductOffering;
import com.dt.platform.catalog.ProductOffering;
import com.dt.platform.catalog.SalesContext;
import com.dt.platform.common.Channel;
import com.dt.platform.common.enums.BusinessProcess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class CartService  {

    @Autowired
    private CatalogFeignClient catalogFeignClient;
    @Autowired
    private CartFeignClient cartFeignClient;

    @Tool(name = "CreateCartforProduct",
            description = "Create Basket for product  variant Id and for a given channel")
    public Cart createCart(String variantId, String channelId) {
        Cart cart = new Cart();
        List<CartLineItem> cartLineItems = new ArrayList<>();
        CartLineItem cartLineItem = new CartLineItem();
        cartLineItem.setBundledCart(false);
        cartLineItem.setRelationshipId(null);
        cartLineItem.setBusinessInteraction(BusinessProcess.ACQUISITION);
        CartItem cartItem = new CartItem();
        cartItem.setAction(Action.ADD);
        cartItem.setQuantity(1);
        cartItem.setId(UUID.randomUUID().toString());
        cartLineItems.add(cartLineItem);
        cartLineItem.setCartItems(Arrays.asList(cartItem));
        cart.setCartLineItems(Arrays.asList(cartLineItem));
        log.info("Creating cart for product variant Id and for a given channel");
        return cart;
    }

    public SalesContext createSalesContext(String variantId, String channelId) {
        SalesContext salesContext = new SalesContext();
        salesContext.setChannel(Channel.builder().id(com.dt.platform.common.enums.Channel.ONE_SHOP.name()).build());
        salesContext.setBusinessProcesses(Collections.singletonList(BusinessProcess.ACQUISITION));
        salesContext.setProductOfferings(Collections.singletonList(ProductOffering.builder().id(variantId).build()));
        return salesContext;
    }

}
