package EcoTrack.server.security;

import EcoTrack.server.service.implementation.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(TokenService tokenService,
                                   UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.equals("/") ||
                path.equals("/login") ||
                path.equals("/register") ||
                path.equals("/index.html") ||
                path.equals("/favPic.png") ||
                path.equals("/logo.png") ||
                path.startsWith("/assets/") ||
                path.startsWith("/api/auth/") ||
                path.startsWith("/api/countries/") ||
                path.matches(".*\\.(js|css|png|jpg|svg|ico)$")) {

            filterChain.doFilter(request, response);
            return;
        }


        String token = getJwtFromCookie(request);

        if (token != null && tokenService.validateToken(token)) {
            try {
                String email = tokenService.getEmailFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (UsernameNotFoundException e) {

                Cookie invalidCookie = new Cookie("jwtToken", null);
                invalidCookie.setHttpOnly(true);
                invalidCookie.setPath("/");
                invalidCookie.setMaxAge(0);
                response.addCookie(invalidCookie);
            } catch (Exception e) {
                System.err.println("JWT authentication error: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}