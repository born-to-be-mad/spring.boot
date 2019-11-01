package by.dma1979.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * @author dzmitry.marudau
 * @since 2019.11
 */
@RestController
@RequestMapping("/files")
public class DownloadController {

    private static final Logger LOG = getLogger(DownloadController.class);

    @RequestMapping("/download")
    public String doDownloadFile(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Access the request:{}", request.getPathInfo());
        LOG.debug("Cccess the response:{}", response.getHeaderNames());
        return "DownloadPage";
    }
}
