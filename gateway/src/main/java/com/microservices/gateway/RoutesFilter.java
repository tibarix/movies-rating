package com.microservices.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RoutesFilter extends ZuulFilter {

    @Autowired
    private Config routeConfig;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String uri = req.getServletPath();
        boolean isBlocked = routeConfig.getBlocked().stream().anyMatch(uri::contains);
        if(isBlocked){

            throw new RuntimeException("route is blocked");
        }
        System.out.println("URI="+uri+" -- "+routeConfig.getBlocked());

        return null;
    }
}
