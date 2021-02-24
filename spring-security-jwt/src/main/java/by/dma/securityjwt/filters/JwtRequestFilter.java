package by.dma.securityjwt.filters;

import by.dma.securityjwt.services.JwtService;
import by.dma.securityjwt.services.SecuredUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Intercepts all Gwt requests.
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
  @Autowired
  private SecuredUserDetailsService userDetailsService;

  @Autowired
  private JwtService jwtService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    String userName = null;
    String token = null;
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      token = authHeader.substring("Bearer ".length());
      userName = jwtService.extractUsername(token);
    }
    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
      if (jwtService.validateToken(token, userDetails)) {
        authenticate(request, userDetails);
      }
    }
    filterChain.doFilter(request, response);

  }

  private void authenticate(HttpServletRequest request, UserDetails userDetails) {
    UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
