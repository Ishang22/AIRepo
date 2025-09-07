package com.dt.mcp.server.model;


import java.io.Serializable;
import java.util.List;

import com.dt.platform.cart.CartItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.dt.platform.common.Characteristic;
import com.dt.platform.common.enums.BusinessProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartLineItem implements Serializable {

    private static final long serialVersionUID = 38815699140130246L;

    private boolean showQuantityStepper;

    private Integer quantity;

    private Integer previousQuantity;

    private Integer maxQuantityAllowed;

    private List<CartItem> cartItems;


    /**
     * On the basis of the flag FE can decide whether to show the basket as bundled cart item or not
     */
    private boolean bundledCart;
    /**
     * Used to show translations on UI.
     * translations are --- 'My Free Plan' in case of tariffNamePrefixWithDevice
     * and 'My plan' for  tariffNamePrefix.
     */
    private boolean extraOnTopAttached;

    private boolean eligibleForExtraOnTop;

    private String tariffNamePrefixKey;

    private BusinessProcess businessInteraction;

    private String businessInteractionVariant;


    private Boolean isFixedCartItem;

    private Boolean isOTTCartLineItem;

    private String connectionAddress;

    private Boolean secondDeviceAllowed;

    private String relationshipId;

    private Boolean isAllowSimoAdditionalDevice;

    private String deviceCategorySlug;

    private Boolean tasDiscountCapable;


    private Boolean isHardwareSolo;

    private List<Characteristic> characteristics;

    private Boolean nonTelekomOfferEligible;

    @JsonProperty
    private Boolean eSimToggle;

    private Boolean deviceOnly;


    private Boolean isIncompleteCartLineItem;

    private String tariffDescription;
}
