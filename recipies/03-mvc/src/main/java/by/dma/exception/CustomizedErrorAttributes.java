package by.dma.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Adds the original request parameters to the model next to the default attributes.
 *
 * @author : Dzmitry Marudau
 * @created at : 00:37
 * @since : 2019.11
 **/
public class CustomizedErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.put("parameters", webRequest.getParameterMap());
        return errorAttributes;
    }
}
