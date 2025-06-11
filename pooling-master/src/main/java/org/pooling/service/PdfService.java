package org.pooling.service;

import jakarta.servlet.http.HttpServletResponse;
import org.pooling.domain.business.Ride;
import org.pooling.domain.user.AppUser;

public interface PdfService {
    public void generatePdf(AppUser appUser, HttpServletResponse response);
    public void generatePdf(Ride ride, HttpServletResponse response);
}
