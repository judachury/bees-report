package org.beesden.report.service;

import org.beesden.report.model.ReportItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReportItemService extends Service<ReportItem> {

	public ReportItemService() {
		super(ReportItem.class.getName());
	}

	public String getQuerySearch(String keywords, String sort) {
		String dbQuery = " JOIN p.variants v WHERE (";
		String[] searches = keywords.split("[ _-]");
		for (int i = 0; i < searches.length; i++) {
			dbQuery += (i == 0 ? "" : " OR ") + "p.heading LIKE '%" + searches[i] + "%' OR v.name LIKE '%" + searches[i] + "%'";
		}
		dbQuery += ") AND " + getStatus("p", 1);
		if (sort != null && !sort.isEmpty()) {
			dbQuery += " ORDER BY " + (sort.startsWith("price") ? "v." : "p.") + sort.replaceAll("_", " ");
		}
		return dbQuery;
	}
}
