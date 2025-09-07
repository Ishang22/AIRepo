package com.dt.mcp.server;

import java.io.Serializable;
import java.util.List;

import com.dt.mcp.server.model.CartLineItem;
import com.dt.platform.cart.CartItem;
import org.springframework.web.util.HtmlUtils;

import com.dt.platform.cart.Contact;
import com.dt.platform.cart.constants.Action;
import com.dt.platform.cart.constants.Status;
import com.dt.platform.catalog.enums.UserSelectedPrice;
import com.dt.platform.common.enums.BusinessProcess;
import com.dt.platform.party.PartyPrivacyProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public class Cart implements Serializable {
        private static final long serialVersionUID = 2657554780587481784L;
        private String id;
        private Integer totalItems;
        private List<CartItem> existingItems;
        private List<CartLineItem> cartLineItems;
        private UserSelectedPrice summaryMainPriceType;
        private Status status;
        private Boolean magentaBenefitsEnabled;
        @JsonIgnore
        private List<Contact> contacts;
        @JsonIgnore
        private List<PartyPrivacyProfile> privacyProfiles;
        @JsonIgnore
        private String installment;
        @JsonIgnore
        private String upFrontPaymentMethodId;
        @JsonIgnore
        private String installmentPaymentMethodId;
        @JsonIgnore
        private String billType;
        @JsonIgnore
        private String shippingType;

        private String preLoadBasketUrl;

        private Boolean preLoadCartRequest;
        /**
         * For 'add to basket' request if there already exists a cart for the user,
         * this field will contain the cart that exists already
         */
        private Cart existingCart;
        /**
         * When set to true in response, UI will show two carts to user with a choice
         * to keep existing cart or replace the existing cart with new one.
         * @see Cart#existingCart is expected to always contain a valid cart when this flag is set to true.
         */
        private boolean swapEligible;
        /**
         * Will dictate if the user wants to replace the existing cart with a new cart.
         * The request in this case will only contain the new cart and existing cart as usual
         * will be fetched from shopping-cart dps.
         */
        private boolean swapRequest;
        /**
         * field describe the validity of coupon code, whether applied coupon is a valid or invalid.
         */
        private boolean couponCodeValid;
        private String couponCode;
        private List<BusinessProcess> selectedBusinessProcesses;
        private String term;
        private Double totalDiscount;
        private String campaignId;
        public void setCampaignId(String campaignId) {
            this.campaignId = (campaignId != null) ? HtmlUtils.htmlEscape(campaignId).replaceAll("<[^>]+>|&[^;]+;", " ") : null;
        }
        private String documentId;
        private String fields;
        private String contextPath;
        //temp field as hack due to https://jira.dtoneapp.telekom.net/browse/ECDEV-12222 and https://jira.dtoneapp.telekom.net/browse/OSDR-3387
        private boolean applyBusinessInteraction;
        //OSUS-823 - show voucher for business process as per CMS
        private boolean voucherVisibility;
        private String removedCoupon;
        // OSUS-784 - default selection of Extra-AddOn in case of Pre-Sales Flow
        private Boolean preSalesFlow;
        private Double totalCartRevenue;
        private Double totalTariffRevenue;
        private Double totalCredits;
        private Boolean startFeeEnabled;
        private Action operationType;
        private boolean preSalesFlowCartSwap;
        private Boolean allowMNP;
        private String tariffId; // OSUS-1194. Used to identify tariff used in two device bundle
        private String firstDeviceVariantId; // OSUS-1194. Used to identify first device in two device bundle
        private Boolean isSecondDeviceAddRequest; // Only meant for internal processing
        // OSUS-1347

        private boolean hideMagentaBenefitToggle;
        private Boolean verifyCartDetails;

        private Boolean nsoDiscountCapable;
        private Boolean isHardwareSolo;
        @JsonIgnore
        private Boolean skipValidateCart;
        @JsonIgnore
        private Boolean isAddDeviceToSIMORequest;
        private Boolean isPermalink;
        private boolean isMultipleOnTopAddonEnabled;
        private boolean fixedVoucherVisibility;
        private Double loyaltyAmount;
        private Boolean activateMagentaBenefits;
        private Boolean showChangeTariffButton;


        @JsonIgnore
        private String itemId;
        @JsonIgnore
        private String parentAddonId;
        private boolean isEligibleTariffMissing;
        private Boolean redirectionForOttCart;
        private boolean magentaOneItemPresent;
        private Boolean showCouponInapplicablePopUp;
        private Boolean has3PProductInInventory;
        private Boolean has2PProductInInventory;
        private Boolean hasSportsOTTTariffInInventory;
        private Boolean isOttHighTrafficJourney;

}
