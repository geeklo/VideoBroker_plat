package com.ivr.plat.util;
/**
 * 基本工具类
 * @author liugeng
 *
 */
public class BaseUtil {
	/**
	 * 日志打印Jdbctemplate的sql
	 * @param sql   sql语句
	 * @param args	参数
	 * @return String
	 */
	public static String logSQL(String sql, Object args[]) {
		if (args == null){
			return sql;
		}
		for (int i = 0; i < args.length; i++) {
			if (args[i] != null) {
				String param = args[i].toString().replace("$", "\\$");
				sql = sql.replaceFirst("\\?", param.length() == 0 ? param : "\\'" + param + "'");
			} else {
				sql = sql.replaceFirst("\\?", "null");
			}
		}
		return sql;
	}
}
