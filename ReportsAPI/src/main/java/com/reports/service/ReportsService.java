package com.reports.service;

import java.io.IOException;
import java.util.List;

import com.reports.requestdto.SearchRequest;
import com.reports.responsedto.SearchResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportsService {

	public List<String> getAllUniquePlanNames();

	public List<String> getAllUniquePlanStatuses();

	public List<SearchResponse> getAllEligibilityDetails(SearchRequest searchRequest);

	public void generateExcel(HttpServletResponse httpServletResponse) throws IOException;

	public void generatePdf(HttpServletResponse httpServletResponse) throws Exception;
}
