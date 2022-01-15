package com.zhw.cloud.microservicegatewayzuulfilter.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul 拦截器
 *
 * 如果禁用拦截器，可以设置：zuul.<SimpleClassName>.<filterType>.disable=true
 * 例如禁用当前拦截器：在 yaml 文件配置： zuul.PreRequestLogFiler.pre.disable=true
 * @Author zhw
 * @since 2022/1/12
 */
@Slf4j
public class PreRequestLogFiler extends ZuulFilter {
    /**
     * 返回过滤器的类型。有pre、route、post、error等几种取值
     * zuul 注释
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 返回一个int 值来指定过滤器的执行顺序，不同的过滤器允许返回相同数字
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 通过返回 true 或 flase ,判断是否要执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("send {} request to {}",request.getMethod(),request.getRequestURI());
        return null;
    }
}
