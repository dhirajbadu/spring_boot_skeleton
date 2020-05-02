package com.realestate.re;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.Conventions;
import org.springframework.util.Assert;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.*;
import java.util.Arrays;
import java.util.EnumSet;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ReApplication.class);
	}


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //servletContext.addFilter("multipartFilter", new MultipartFilter());
        registerFilters(servletContext , true ,new MultipartFilter());

        super.onStartup(servletContext);
    }

    private void registerFilters(ServletContext servletContext, boolean insertBeforeOtherFilters, Filter... filters) {
        Assert.notEmpty(filters, "filters cannot be null or empty");
        Filter[] var4 = filters;
        int var5 = filters.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Filter filter = var4[var6];
            if (filter == null) {
                throw new IllegalArgumentException("filters cannot contain null values. Got " + Arrays.asList(filters));
            }

            String filterName = Conventions.getVariableName(filter);
            this.registerFilter(servletContext, insertBeforeOtherFilters, filterName, filter);
        }

    }

    private final void registerFilter(ServletContext servletContext, boolean insertBeforeOtherFilters, String filterName, Filter filter) {
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        if (registration == null) {
            throw new IllegalStateException("Duplicate Filter registration for '" + filterName + "'. Check to ensure the Filter is only configured once.");
        } else {
            registration.setAsyncSupported(true);
            EnumSet<DispatcherType> dispatcherTypes = this.getSecurityDispatcherTypes();
            registration.addMappingForUrlPatterns(dispatcherTypes, !insertBeforeOtherFilters, "/*");
        }
    }

    protected EnumSet<DispatcherType> getSecurityDispatcherTypes() {
        return EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);
    }
}
