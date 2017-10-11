package com.sanyka.weixin.api.mp.message.service;

import java.util.List;

import com.sanyka.weixin.api.mp.message.MsgTypeEnum;
import com.sanyka.weixin.api.mp.message.ReturnMessage;
import com.sanyka.weixin.api.mp.message.domain.ArticleDo;

/**
 * 文本消息
 * 
 * @author OF 2017年9月4日
 */

public class NewsMessage extends ReturnMessage {
	private static final long serialVersionUID = 1L;
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<ArticleDo> Articles;

	public NewsMessage() {
		this.setMsgType(MsgTypeEnum.News.toString());
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<ArticleDo> getArticles() {
		return Articles;
	}

	public void setArticles(List<ArticleDo> articles) {
		Articles = articles;
	}
}
