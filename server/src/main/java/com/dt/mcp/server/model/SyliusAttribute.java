package com.dt.mcp.server.model;

import com.dt.platform.catalog.ProductSpecificationCharacteristicRelationship;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyliusAttribute  {
    private String code;

    private List<SyliusTranslation> translations;

    private String valueType;

    @JsonProperty("isConfigurable")
    private boolean configurable;

    @JsonProperty("isVisible")
    private boolean visible;

    private String entityType;

    @JsonProperty("isCustomerVisible")
    private boolean customerVisible;

    private String currencyCode;

    private int minCardinality;

    private int maxCardinality;

    private List<SyliusAttributeCharacteristicValue> characteristicValues;

    private List<SyliusValidationRule> validationRules;

    // Introduced as part of OSUS-1343
    private List<ProductSpecificationCharacteristicRelationship> productSpecCharRelationship;

    private Integer priority;

    @JsonProperty("isDeleteDx")
    private Integer deleteDx;

    @JsonProperty("isUpdateDx")
    private Integer updateDx;

    @JsonProperty("isAddDx")
    private Integer addDx;
}
