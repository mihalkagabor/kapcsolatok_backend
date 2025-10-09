package org.mihalka.kapcsolatok_backend.sercurity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter  extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void  doFilterInternal (
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Egyes végpontokat kihagyunk a JWT ellenőrzés alól (pl. login, token frissítés, regisztráció)
        String path= request.getRequestURI();
        if(path.startsWith("api/auth/login") ||
                path.startsWith("/api/auth/refresh-token") ||
                (path.startsWith("/api/user/register") && request.getMethod().equals("POST")) ) {
            filterChain.doFilter(request,response);
            return;
        }

        // Authorization fejléc beolvasása
        final String authHeader = request.getHeader("Authorization");

        // Ha nincs token, vagy nem "Bearer ..." formátumú → továbbengedjük a kérést
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // A token kivágása a "Bearer " rész után
        final String jwt = authHeader.substring(7);


        try {
            // Felhasználónév kiolvasása a tokenből
            final String username = jwtService.extractUsername(jwt);

            // Ha van felhasználónév és még nincs bejelentkezve a SecurityContext-ben
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Ha a token érvényes, akkor beléptetjük a felhasználót
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    // Beállítjuk az authentikációt a SecurityContext-be
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // Tovább engedjük a kérést a következő filterhez
            filterChain.doFilter(request, response);

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // Ha a token lejárt → 401 UNAUTHORIZED válasz
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("JWT token expired");
            response.getWriter().flush();
        }


    }

}
