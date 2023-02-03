package com.example.testproject.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExporterImpl {
    void export(HttpServletResponse response, Long id) throws IOException;
}
