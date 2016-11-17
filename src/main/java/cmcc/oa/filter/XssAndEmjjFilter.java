package cmcc.oa.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * xss和emjj表情过滤
 *
 * @author renlinggao
 * @Date 2016年6月21日
 */
public class XssAndEmjjFilter implements Filter {

    FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssAndEmjjHttpServletRequestWrapper(
                (HttpServletRequest) request), response);
    }

}
