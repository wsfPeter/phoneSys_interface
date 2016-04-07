package com.net.toooen.entity;

import java.util.ArrayList;
import java.util.List;

public class News {
    /**
     * @Fields id : 资讯ID
     */
    private Integer id;

    /**
     * @Fields categoryId : 栏目ID
     */
    private Integer categoryId;

    /**
     * @Fields digest : 摘要
     */
    private String digest;

    /**
     * @Fields title : 标题
     */
    private String title;

    /**
     * @Fields content : 资讯内容（在分享页面中会用到）
     */
    private String content;

    /**
     * @Fields titleImage : 标题图片
     */
    private List<String> titleImage = new ArrayList<String>();

    /**
     * @Fields pviews : 浏览次数
     */
    private Integer pviews;

    /**
     * @Fields voteCount : 投票次数
     */
    private Integer voteCount;

    /**
     * @Fields type : 资讯类型：1 文字；2 图片
     */
    private Integer type;

    /**
     * @Fields isAtlas : b'0'
     */
    private Boolean isAtlas;

    /**
     * @Fields createDate : 创建时间
     */

    private String createDate;

    /**
     * @Fields isView : 是否已读
     */
    private Boolean isView = false;

    /**
     * @Fields layout : 布局形式（1：左右；2：上下；3：三张标题图片一次排开）
     */
    private Integer layout;

    private String source;

    private Integer commentNum;

    private Boolean isShare;

    private Boolean isPulish;

    private Boolean isComment;
    
    /**
     * 图片类型 1 小横幅 2 大横幅
     */
    private Integer imageType;

    public Boolean getIsView() {
        return isView;
    }

    public void setIsView(Boolean isView) {
        this.isView = isView;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(List<String> titleImage) {
        this.titleImage = titleImage;
    }

    public Integer getPviews() {
        return pviews;
    }

    public void setPviews(Integer pviews) {
        this.pviews = pviews;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsAtlas() {
        return isAtlas;
    }

    public void setIsAtlas(Boolean isAtlas) {
        this.isAtlas = isAtlas;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLayout() {
        return layout;
    }

    public void setLayout(Integer layout) {
        this.layout = layout;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Boolean getIsComment() {
        return isComment;
    }

    public void setIsComment(Boolean isComment) {
        this.isComment = isComment;
    }

    public Boolean getIsShare() {
        return isShare;
    }

    public void setIsShare(Boolean isShare) {
        this.isShare = isShare;
    }

    public Boolean getIsPulish() {
        return isPulish;
    }

    public void setIsPulish(Boolean isPulish) {
        this.isPulish = isPulish;
    }

	public Integer getImageType() {
		return imageType;
	}

	public void setImageType(Integer imageType) {
		this.imageType = imageType;
	}

}
