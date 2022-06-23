package parkhouse.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = "/*" )
public class SecurityCORSFilter implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type");
    response.setHeader("X-Frame-Options", "sameorigin");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Referrer-Policy", "same-origin");
    response.setHeader("Permissions-Policy", "geolocation=(), midi=(), sync-xhr=(), microphone=(), camera=(), magnetometer=(), gyroscope=(), fullscreen=(self), payment=(), usb=()");
    // response.setHeader("Strict-Transport-Security", "max-age=631139040; includeSubdomains; preload;");
    // response.setHeader("Content-Security-Policy", "default-src 'self' http://kaul.inf.h-brs.de");
    chain.doFilter(req, res);
  }

  @Override
  public void destroy() {}

  @Override
  public void init(FilterConfig arg0) throws ServletException {}

}