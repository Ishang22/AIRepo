package com.dt.mcp.server.interceptor;

import com.dt.mcp.server.model.HeaderContext;
import com.dt.platform.feignclient.interceptor.AdditionalHeaderProvider;
import com.dt.platform.metrics.constant.MetricConstant;
import com.dt.platform.metrics.context.MetricsContextParam;
import com.dt.platform.rest.constant.RequestConstants;
import com.dt.platform.utils.CommonConstants;
import com.dt.platform.utils.context.ContextParamDefault;
import com.dt.platform.utils.context.DTContext;
import com.dt.platform.utils.context.ServiceContextUtils;
import com.dt.platform.utils.http.HttpUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;
import java.util.stream.Stream;

import static com.dt.platform.rest.constant.RequestConstants.X_CMS_AUTHORIZATION;

@Slf4j
public class FeignInternalMcpRequestInterceptor implements RequestInterceptor {

    @Value("${application.country:#{null}}")
    private String country;

    @Value("${auth.outgoing.microservice.call.enabled:false}")
    private boolean isOutgoingAuthEnabled;
    @Value("${local-profile.enabled:false}")
    private boolean isLocalProfileEnabled;

    @Autowired
    Environment environment;

    @Autowired
    @Qualifier("feignInternalMicroServiceHeader")
    private AdditionalHeaderProvider additionalHeaderProvider;

    @Override
    public void apply(RequestTemplate template) {

        Map<String,String> receivedHeader = HeaderContext.get();

        boolean isLocalProfileAvailable =
                Stream.of(environment.getActiveProfiles()).anyMatch(profile -> profile.contains("local"));


        log.debug("inside thread::" + Thread.currentThread().getName());

        log.info("Inside Internal Micro Service Interceptor.....");

        additionalHeaderProvider.addHeaders(template);

        template.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        template.header(CommonConstants.DPS_CUSTOMIZATION_ENABLED, "true");
        template.header(CommonConstants.CALLER, ServiceContextUtils.getServiceId());
        template.header(RequestConstants.COUNTRY, receivedHeader.get(CommonConstants.COUNTRY));
        template.header("x-request-country",receivedHeader.get(CommonConstants.COUNTRY));
        template.header("x-request-status",receivedHeader.get("x-request-status"));
        template.header("content-language",receivedHeader.get("content-language"));
        template.header("hal_authorization",receivedHeader.get("hal_authorization"));
        template.header("x-request-tracking-id", receivedHeader.get("x-request-tracking-id"));
        template.header("Cookie","wt_cookiecontrol=1; __ctmou_lej6ogei=cuid-1737614551381-9a5WWbqz.019491e5cedd0002f26034e506be0507500be06d0093c; _fbp=fb.1.1737614551647.464816123905753801; apt.uid=AP-YFGMCGUNNIFB-2-1738077239432-11684372.0.2.6b933aba-006b-4c12-b4b5-a0ddc2adfcf4; _ga_0C4M1PWYZ7=GS1.1.1738077243.1.0.1738077246.0.0.0; _ga_T11SF3WXX2=GS1.1.1738077243.1.0.1738077246.57.0.0; _ga_K2SPJK2C73=GS1.1.1738077244.1.0.1738077246.58.0.0; __exponea_etc__=b02ac8f9-1ebc-4f87-a371-8f58c082b46b; _hjSessionUser_1813104=eyJpZCI6ImM1NmU0MjY4LWE2M2YtNWIwYS1iM2NhLWFlZDMzYzVkZjIyZSIsImNyZWF0ZWQiOjE3NDAxMjI0NDgzNjIsImV4aXN0aW5nIjp0cnVlfQ==; wt3_eid=%3B865234457892410%7C4174547323700652104%3B568484681972144%7C4174547323700652104; _ga=GA1.2.332556516.1738077243; _ga_A2ABC2ABCD=GS2.1.s1752479801$o23$g0$t1752481058$j60$l0$h0; tel_usr_ids=%7B%7D; CONSENTMGR=consent:true%7Cts:1753876354280%7Cc1:1%7Cc3:1%7Cc6:0%7Cc7:1%7Cc10:1; _uetvid=52f7919042a011efbdbdb188f526f39e; wt_rla=865234457892410%2C22%2C1753876358471; utag_main=v_id:019491e5cedd0002f26034e506be0507500be06d0093c$_sn:107$_se:11$_ss:0$_st:1753878177159$dc_visit:93$ses_id:1753876351355%3Bexp-session$_pn:1%3Bexp-session$dc_event:9%3Bexp-session$dc_region:eu-central-1%3Bexp-session; dtCookie=v_4_srv_37_sn_C44E910F2EB387CDC8BAE11BA38939EB_perc_100000_ol_0_mul_1_app-3Aea7c4b59f27d43eb_1; rxVisitor=1756878844596275E9P940ORSCM2O80BT3689M8FJ9SBA; dtSa=-; dtPC=37$78844590_886h-vIDQHIEFPSKSKLWACUNMUFUUERWQDMQMP-0e0; rxvt=1756887860375|1756886060375; SESSION=ZjNlY2Q2YjgtZDM3Mi00YWUzLWIzOGQtNDEzNDYxNTI1ZmIz; SESSION=ZWZkODZlOWQtMzhjOS00YjYzLTgxNjktN2VjZTI0NjcyMTIy");

        //template.header()
    }

}