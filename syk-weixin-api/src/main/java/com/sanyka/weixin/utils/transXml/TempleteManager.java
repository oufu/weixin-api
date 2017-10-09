package com.sanyka.weixin.utils.transXml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 类名： TempleteManager<br>
 * 功能：交易模板管理类<br>
 * 版本： 1.0<br>
 * 日期： 2016年1月8日<br>
 * 作者： OF<br>
 * 版权：开科维识<br>
 * 说明：freemarker 模板生成。<br>
 */
public class TempleteManager {
	private static TempleteManager tplm = null;

	private Configuration configuration;
	private String templetePath = "/conf/templete/";

	private TempleteManager() {
		configuration = new Configuration();
		try {
			configuration.setClassForTemplateLoading(this.getClass(),
					templetePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Template getTemplate(String name) throws IOException {
		if (tplm == null) {
			tplm = new TempleteManager();
		}
		return tplm.configuration.getTemplate(name);
	}

	/**
	 * 生成template
	 * 
	 * @param templatefile
	 *            模板文件
	 * @param param
	 *            参数
	 * @return
	 * @throws IOException
	 *             异常
	 * @throws TemplateException
	 */
	public static String process(String templatefile, Map param)
			throws IOException, TemplateException {
		Template template = TempleteManager.getTemplate("trans-" + templatefile
				+ "-template.ftl");
		StringWriter sw = new StringWriter();
		template.process(param, sw);
		return sw.toString().replaceAll(">(\\s*)<", "><").trim();
	}
}