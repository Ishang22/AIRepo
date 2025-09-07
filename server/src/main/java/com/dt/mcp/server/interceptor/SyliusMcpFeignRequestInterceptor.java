package com.dt.mcp.server.interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyliusMcpFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("country", "mk");
        template.header("Cookie","SESSION=MjkyMDY2YjItNDQwMS00ZTZhLThmNzYtMTY5NDY2MmRkYjVi");
    }
}
//slc