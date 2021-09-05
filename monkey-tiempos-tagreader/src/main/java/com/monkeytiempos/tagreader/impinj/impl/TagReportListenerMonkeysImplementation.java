package com.monkeytiempos.tagreader.impinj.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.impinj.octane.ImpinjReader;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;

@Service
public class TagReportListenerMonkeysImplementation implements TagReportListener {

	@Override
	public void onTagReported(ImpinjReader reader, TagReport report) {
		List<Tag> tags = report.getTags();
		for (Tag t : tags) {
			System.out.println("\t\t\t\tMonkeys implementation: " + t.getEpc().toString());
		}
	}

}
