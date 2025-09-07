package com.dt.mcp.server.service;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dt.mcp.server.feign.SyliusMcpApiService;
import com.dt.mcp.server.model.CreateAttributeCmd;
import com.dt.mcp.server.model.HeaderContext;
import com.dt.mcp.server.model.SyliusAttribute;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:<br>
 * Date: 04/09/25-12:08 pm
 *
 * @author ishangarg
 * @since
 */

@Service
@Slf4j
public class AttributeService
{

    @Autowired
    private SyliusMcpApiService syliusMcpApiService;

    @Autowired
    private HttpServletRequest request;

    @Tool(name = "createAttribute",
            description = "Create Attributes for syllius")
    public SyliusAttribute createAttributes(String syliusAttribute) throws JsonProcessingException {
        log.info("Creating attributes for syllius {}",syliusAttribute);

        Map<String, String> headers = HeaderContext.get();
        System.out.println("Headers in Tool: " + headers);

        // Use headers if needed, e.g., forwarding
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null) {
            headers.forEach(httpHeaders::add);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.readValue(syliusAttribute, SyliusAttribute.class);
        SyliusAttribute syliusAttribute2 = mapper.readValue(syliusAttribute, SyliusAttribute.class);
        syliusAttribute2.setTranslations(new ArrayList<>());
        syliusAttribute2.setVisible(true);
        syliusAttribute2.setConfigurable(true);
        syliusAttribute2.setValueType("numeric");
        syliusAttribute2.setAddDx(1);
        syliusAttribute2.setDeleteDx(1);
        syliusAttribute2.setUpdateDx(1);
        syliusMcpApiService.createUpdateAttribute(syliusAttribute2);
        return new SyliusAttribute();
    }

    @Tool(name = "validateAttribute",
            description = "validateAttributes for syllius")
    public SyliusAttribute validateAttributes(String syliusAttribute) {
        log.info("validateAttributes for syllius {}",syliusAttribute);
        return new SyliusAttribute();
    }


    @Tool(name = "updateAttribute",
            description = "updateAttribute for syllius")
    public SyliusAttribute updateAttribute(String syliusAttribute) {
        log.info("updateAttributes for syllius {}",syliusAttribute);
        return new SyliusAttribute();
    }
}
