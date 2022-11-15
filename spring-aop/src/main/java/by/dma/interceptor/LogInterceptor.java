package by.dma.interceptor;

import java.util.UUID;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Spring Boot Interceptors are useful tools for intercepting the HTTP request process.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        UUID uuid = UUID.randomUUID();
        request.setAttribute("start", System.currentTimeMillis());
        request.setAttribute("request-id", uuid);
        log.info("{} - calling {}", uuid, request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        log.info("{} - response in {} ms",
                request.getAttribute("request-id"),
                System.currentTimeMillis() - (long) request.getAttribute("start"));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception exception) throws Exception {
        log.info("{} - completed in {} ms",
                request.getAttribute("request-id"),
                System.currentTimeMillis() - (long) request.getAttribute("start"));
    }
}
