package com.example.absheronikbackend.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    // Хранилище ведер для каждого IP-адреса
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    // Метод создания нового ведра (15 запросов в 1 минуту)
    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.classic(15, Refill.greedy(15, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Ограничиваем только POST запросы на регистрацию (или все запросы - на твой выбор)
        String path = request.getRequestURI();
        if (path.startsWith("/api/students")) {

            String clientIp = getClientIP(request);
            Bucket bucket = buckets.computeIfAbsent(clientIp, k -> createNewBucket());

            // Пытаемся списать 1 токен
            if (!bucket.tryConsume(1)) {
                // Если лимит превышен — возвращаем 429 Too Many Requests
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.setContentType("text/plain;charset=UTF-8");
                response.getWriter().write("Çoxlu sorğu göndərdiniz! Zəhmət olmasa 1 dəqiqə gözləyin. (Cлишком много запросов!)");
                return;
            }
        }

        // Если лимит не превышен — пропускаем запрос дальше
        filterChain.doFilter(request, response);
    }

    // Получаем реальный IP-адрес клиента (учитывая прокси Render / Cloudflare / Vercel)
    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isEmpty()) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0].trim();
    }
}