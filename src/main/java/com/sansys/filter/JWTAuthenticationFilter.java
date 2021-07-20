package com.sansys.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sansys.service.CustomUserDetailsService;
import com.sansys.utility.ApplicationConstants;
import com.sansys.utility.JWTUtility;

import io.jsonwebtoken.ExpiredJwtException;

/*
 * Copyright (C) SanSys Pvt. Ltd. SanSys is a registered trademark and the SanSys
 * graphic logo is a trademark of SanSys Pvt. Ltd.
 * SanSys reserves all the right for this source code. 
 * You should not modify or reuse without the noticing it to SanSys. 
 * And need to provide credits where applicable. 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific governing permissions and
 * limitations under the License.
 */

/**
 * 
 *
 * @Author : Sanjeev Kulkarni
 * @Created On : 03-Jul-2021 11:43:15 pm
 *
 */

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JWTUtility jwtUtikity;
    
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    /* (non-Javadoc)
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        
        String authorizationHeader = request.getHeader(ApplicationConstants.AUTHORIZATION);
        String userName = null;
        String token = null;
        
        if(authorizationHeader != null && authorizationHeader.startsWith(ApplicationConstants.BEARER)) {
            token = authorizationHeader.substring(7);
            
            try {
                
                userName = jwtUtikity.getUsernameFromToken(token);
                
            }catch(ExpiredJwtException e){
                System.out.println("JWT Token Expired !!!");
                String isRefreshToken = request.getHeader(ApplicationConstants.IS_REFRESH_TOKEN);
                String requestURL = request.getRequestURL().toString();
                // allow for Refresh Token creation if following conditions are true.
                if (isRefreshToken != null && isRefreshToken.equals(ApplicationConstants.TRUE) && requestURL.contains(ApplicationConstants.REFRESHTOKEN)) {
                    allowForRefreshToken(e, request);
                } else
                    request.setAttribute(ApplicationConstants.EXCEPTION, e);

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } else {
            System.out.println("Invalid Token!");
        }
        filterChain.doFilter(request, response);
    }
    
    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

        // create a UsernamePasswordAuthenticationToken with null values.
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                null, null, null);
        // After setting the Authentication in the context, we specify
        // that the current user is authenticated. So it passes the
        // Spring Security Configurations successfully.
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        // Set the claims so that in controller we will be using it to create
        // new JWT
        request.setAttribute(ApplicationConstants.CLAIMS, ex.getClaims());

    }

}
