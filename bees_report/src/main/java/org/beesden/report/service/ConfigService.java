package org.beesden.report.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.beesden.report.model.Config;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class ConfigService extends Service<Config> {

	public ConfigService() {
		super(Config.class.getName());
	}

	public Map<String, Object> getConfig(HttpServletRequest request) {
		// Get site configuration settings
		Map<String, Object> config = new HashMap<String, Object>();
		List<Config> configList = findAll("");
		for (Config configItem : configList) {
			if (configItem.getType() != null && configItem.getType().equals("map")) {
				try {
					HashMap<String, Object> result = new ObjectMapper().readValue(configItem.getValue(), HashMap.class);
					config.put(configItem.getName(), result);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (configItem.getType() != null && configItem.getType().equals("integer")) {
				config.put(configItem.getName(), Integer.parseInt(configItem.getValue()));
			} else {
				config.put(configItem.getName(), configItem.getValue());
			}
		}
		return config;
	}

}
