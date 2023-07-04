package hruler.portfolio.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Slf4j
public class RequestLogInterceptor implements HandlerInterceptor {
    private static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        String logId = UUID.randomUUID().toString();
        request.setAttribute(LOG_ID, logId);

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
        }

        log.info("REQUEST INFO [{}][{}][{}][{}]", logId, method, requestURI, handler);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = request.getAttribute(LOG_ID).toString();
        log.info("RESPONSE INFO [{}][{}]", logId, requestURI);
        if (ex != null) {
            log.error("ERROR INFO", ex);
        }
    }
}
