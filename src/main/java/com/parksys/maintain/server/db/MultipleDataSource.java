package com.parksys.maintain.server.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源
 * @see 手动切换数据源
 * @see MultipleDataSource.setDataSourceKey("dataSource");
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
	
	private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();
	private static final String defaultTargetDataSource = "dataSource";
	
    /**
     * 手动切换数据源
     * @param dataSourceName 数据源名称
     */
    public static void setDataSourceKey(String dataSourceName) {
        dataSourceKey.set(dataSourceName);
    }
    
    public static String getDataSourceKey() {
    	String dataSourceName = dataSourceKey.get();
		return dataSourceName == null ? defaultTargetDataSource : dataSourceName;
    }

	@Override
	protected Object determineCurrentLookupKey() {
		String dataSourceName = dataSourceKey.get();
		return dataSourceName == null ? defaultTargetDataSource : dataSourceName;
	}
}
