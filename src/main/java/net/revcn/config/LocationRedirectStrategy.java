package net.revcn.config;

import org.apache.hc.client5.http.impl.DefaultRedirectStrategy;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ProtocolException;
import org.apache.hc.core5.http.protocol.HttpContext;
import net.revcn.constant.KeyConstant;

public class LocationRedirectStrategy extends DefaultRedirectStrategy {

    @Override
    public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        var redirected = super.isRedirected(request, response, context);
        var headers = response.getHeaders();
        for (var header : headers) {
            if (header.getName().equals(KeyConstant.LOCATION_KEY)) {
                context.setAttribute(KeyConstant.LOCATION_KEY, header.getValue());
            }
        }

        var location = context.getAttribute(KeyConstant.LOCATION_KEY);
        response.addHeader(KeyConstant.LOCATION_KEY, location);
        return redirected;
    }
}
