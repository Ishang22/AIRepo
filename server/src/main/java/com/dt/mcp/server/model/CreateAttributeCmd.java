package com.dt.mcp.server.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:<br>
 * Date: 04/09/25-5:21 pm
 *
 * @author ishangarg
 * @since
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAttributeCmd implements Serializable {
    private String syliusAttribute;
    private String authToken;
    private String tenantId;
    private String requestStatus;
}
