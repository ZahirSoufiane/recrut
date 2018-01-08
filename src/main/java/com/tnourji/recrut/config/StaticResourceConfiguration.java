package com.tnourji.recrut.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/***
 * static resource config
 * 
 * @author Michael Tnourji
 *
 */
@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
    
    @Value("#{'${resource.locations}'.split(',')}")
    private String[] locations;
    
    @Value("${photos.partners.dir}")
    private String photos_dir;
    
    @Value("${video.dir}")
    private String videoDir;
    
    @Value("${public.files.dir}")
    private String publicDir;
    
    @Value("${tomcat.ajp.port}")
    int ajpPort;
    
    @Value("${server.httpport}")
    private Integer httpPort;
    
    @Value("${tomcat.ajp.remoteauthentication}")
    private String remoteAuthentication;
    
    @Value("${tomcat.ajp.enabled}")
    boolean tomcatAjpEnabled;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/photoPartner/**")
                .addResourceLocations("file:///" + photos_dir);
        registry.addResourceHandler("/photoCategs/**")
                .addResourceLocations("file:///" + photos_dir + "ym-categs/");
        registry.addResourceHandler("/video/**")
        .addResourceLocations("file:///" + videoDir);
        registry.addResourceHandler("/public/**")
        .addResourceLocations("file:///" + publicDir);
        
        registry.addResourceHandler("/**").addResourceLocations(locations);
    }
    
    /**
     * initialize http connector
     * 
     * @return connector
     */
    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(httpPort);
        connector.setSecure(false);
        connector.setRedirectPort(443);
        
        return connector;
    }
    
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        
        if (tomcatAjpEnabled) {
            Connector ajpConnector = new Connector("AJP/1.3");
            ajpConnector.setProtocol("AJP/1.3");
            ajpConnector.setPort(ajpPort);
            ajpConnector.setSecure(true);
            ajpConnector.setMaxPostSize(21000);
            ajpConnector.setProperty("maxHttpHeaderSize", "6400000");
            ajpConnector.setMaxSavePostSize(21000);
            ajpConnector.setAllowTrace(false);
            ajpConnector.setScheme("https");
            tomcat.addAdditionalTomcatConnectors(ajpConnector);
        }
        
        return tomcat;
    }
    
}