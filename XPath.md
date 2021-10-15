### XPath规则套娃（需v2.0.4及以上版本）
----
套娃依赖自定义爬虫jar，同样需要在自定义json中加入相应的配置，**type=3, api为csp_XPath**，套娃相关规则配置在`ext`字段中，注意：ext字段值**只能是json字符串**
```json
{
    "key": "csp_xpath_94sm",
    "name": "94神马(XPath)",
    "type": 3,
    "api": "csp_XPath",
    "searchable": 1,
    "quickSearch": 1,
    "filterable": 1,
    "ext": "{\r\n  \"ua\": \"\",\r\n  \"homeUrl\": \"http://www.9rmb.com\",\r\n  \"cateNode\": \"//ul[contains(@class,'navbar-nav')]/li/a[contains(@href, '.html') and not(contains(@href, '6'))]\",\r\n  \"cateName\": \"/text()\",\r\n  \"cateId\": \"/@href\",\r\n  \"cateIdR\": \"/type/(\\\\d+).html\",\r\n  \"cateManual\": {},\r\n  \"homeVodNode\": \"//div[@class='col-md-12 movie-item-out']//a[not(contains(@href, '6'))]/parent::*/parent::*/parent::*/div[contains(@class, 'movie-item-out') and position()<10]/div[@class='movie-item']/a\",\r\n  \"homeVodName\": \"/@title\",\r\n  \"homeVodId\": \"/@href\",\r\n  \"homeVodIdR\": \"/show/(\\\\w+).html\",\r\n  \"homeVodImg\": \"/img/@src\",\r\n  \"homeVodMark\": \"/button/text()\",\r\n  \"cateUrl\": \"http://www.9rmb.com/type/{cateId}/{catePg}.html\",\r\n  \"cateVodNode\": \"//div[@class='movie-item']/a\",\r\n  \"cateVodName\": \"/@title\",\r\n  \"cateVodId\": \"/@href\",\r\n  \"cateVodIdR\": \"/show/(\\\\w+).html\",\r\n  \"cateVodImg\": \"/img/@src\",\r\n  \"cateVodMark\": \"/button/text()\",\r\n  \"dtUrl\": \"http://www.9rmb.com/show/{vid}.html\",\r\n  \"dtNode\": \"//div[@class='container-fluid']\",\r\n  \"dtName\": \"//div[@class='col-md-9']//div[@class='col-md-4']//img/@alt\",\r\n  \"dtNameR\": \"\",\r\n  \"dtImg\": \"//div[@class='col-md-9']//div[@class='col-md-4']//img/@src\",\r\n  \"dtImgR\": \"\",\r\n  \"dtCate\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '类型')]/parent::*/following-sibling::*/text()\",\r\n  \"dtCateR\": \"\",\r\n  \"dtYear\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '日期')]/parent::*/following-sibling::*/text()\",\r\n  \"dtYearR\": \"\",\r\n  \"dtArea\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '国家')]/parent::*/following-sibling::*/text()\",\r\n  \"dtAreaR\": \"\",\r\n  \"dtMark\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '状态')]/parent::*/following-sibling::*/text()\",\r\n  \"dtMarkR\": \"\",\r\n  \"dtActor\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '主演')]/parent::*/following-sibling::*/text()\",\r\n  \"dtActorR\": \"\",\r\n  \"dtDirector\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '导演')]/parent::*/following-sibling::*/text()\",\r\n  \"dtDirectorR\": \"\",\r\n  \"dtDesc\": \"//p[@class='summary']/text()\",\r\n  \"dtDescR\": \"\",\r\n  \"dtFromNode\": \"//div[contains(@class,'resource-list')]/div[@class='panel-heading']/strong\",\r\n  \"dtFromName\": \"/text()\",\r\n  \"dtFromNameR\": \"\\\\S+\\\\.(\\\\S+) \\\\(\",\r\n  \"dtUrlNode\": \"//div[contains(@class,'resource-list')]/ul[@class='dslist-group']\",\r\n  \"dtUrlSubNode\": \"/li/a\",\r\n  \"dtUrlId\": \"@href\",\r\n  \"dtUrlIdR\": \"/play/(\\\\S+).html\",\r\n  \"dtUrlName\": \"/text()\",\r\n  \"dtUrlNameR\": \"\",\r\n  \"playUrl\": \"http://www.9rmb.com/play/{playUrl}.html\",\r\n  \"playUa\": \"\",\r\n  \"searchUrl\": \"http://www.9rmb.com/search?wd={wd}\",\r\n  \"scVodNode\": \"//div[@class='movie-item']/a\",\r\n  \"scVodName\": \"/@title\",\r\n  \"scVodId\": \"/@href\",\r\n  \"scVodIdR\": \"/show/(\\\\w+).html\",\r\n  \"scVodImg\": \"/img/@src\",\r\n  \"scVodMark\": \"/button/text()\"\r\n}\r\n"
}
```

### 套娃规则

demo配置写的比较细，不一定所有字段都要有，具体还是自己多试一试

```json
{
	"ua": "",
    // 首页地址 用于获取 分类和首页推荐
	"homeUrl": "http://www.9rmb.com",
    // 分类节点
	"cateNode": "//ul[contains(@class,'navbar-nav')]/li/a[contains(@href, '.html') and not(contains(@href, '6'))]",
    // 分类名
	"cateName": "/text()",
    // 分类id
	"cateId": "/@href",
    // 分类id二次处理正则
	"cateIdR": "/type/(\\d+).html",
    // 手动设置分类，如果手动设置了分类则不使用上面的分类xpath获取分类  例如 "cateManual": {"电影": "1", "电视剧": "2"},
	"cateManual": {},
    // 首页推荐视频的节点
	"homeVodNode": "//div[@class='col-md-12 movie-item-out']//a[not(contains(@href, '6'))]/parent::*/parent::*/parent::*/div[contains(@class, 'movie-item-out') and position()<10]/div[@class='movie-item']/a",
    // 首页推荐视频的名称
	"homeVodName": "/@title",
    // 二次处理正则
    "homeVodNameR": "",
    // 首页推荐视频的id
	"homeVodId": "/@href",
    // 二次处理正则
	"homeVodIdR": "/show/(\\w+).html",
    // 首页推荐视频的图片
	"homeVodImg": "/img/@src",
    // 二次处理正则
    "homeVodImgR": "",
    // 首页推荐视频的简介
	"homeVodMark": "/button/text()",
    // 二次处理正则
    "homeVodMarkR": "",
    // 分类页地址 {cateId} 分类id {catePg} 当前页
	"cateUrl": "http://www.9rmb.com/type/{cateId}/{catePg}.html",
    // 同上面的homeVod字段 分类列表中的视频信息
	"cateVodNode": "//div[@class='movie-item']/a",
	"cateVodName": "/@title",
	"cateVodId": "/@href",
	"cateVodIdR": "/show/(\\w+).html",
	"cateVodImg": "/img/@src",
	"cateVodMark": "/button/text()",
    // 详情页地址 用于获取详情页信息 及 播放列表和地址
	"dtUrl": "http://www.9rmb.com/show/{vid}.html",
    // 详情节点
	"dtNode": "//div[@class='container-fluid']",
    // 视频名
	"dtName": "//div[@class='col-md-9']//div[@class='col-md-4']//img/@alt",
	"dtNameR": "",
    // 视频图片
	"dtImg": "//div[@class='col-md-9']//div[@class='col-md-4']//img/@src",
	"dtImgR": "",
    // 视频分类
	"dtCate": "//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '类型')]/parent::*/following-sibling::*/text()",
	"dtCateR": "",
    // 视频年份
	"dtYear": "//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '日期')]/parent::*/following-sibling::*/text()",
	"dtYearR": "",
    // 视频地区
	"dtArea": "//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '国家')]/parent::*/following-sibling::*/text()",
	"dtAreaR": "",
    // 视频状态
	"dtMark": "//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '状态')]/parent::*/following-sibling::*/text()",
	"dtMarkR": "",
    // 主演
	"dtActor": "//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '主演')]/parent::*/following-sibling::*/text()",
	"dtActorR": "",
    // 导演
	"dtDirector": "//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '导演')]/parent::*/following-sibling::*/text()",
	"dtDirectorR": "",
    // 视频简介
	"dtDesc": "//p[@class='summary']/text()",
	"dtDescR": "",
    // 播放源节点
	"dtFromNode": "//div[contains(@class,'resource-list')]/div[@class='panel-heading']/strong",
    // 播放源名称
	"dtFromName": "/text()",
    // 二次处理正则
	"dtFromNameR": "\\S+\\.(\\S+) \\(",
    // 播放列表节点
	"dtUrlNode": "//div[contains(@class,'resource-list')]/ul[@class='dslist-group']",
    // 播放地址节点
	"dtUrlSubNode": "/li/a",
    // 播放地址
	"dtUrlId": "@href",
    // 二次处理正则
	"dtUrlIdR": "/play/(\\S+).html",
    // 剧集名称
	"dtUrlName": "/text()",
    // 二次处理正则
	"dtUrlNameR": "",
    // 播放页面的地址 {playUrl} 对应上面 dtUrlId 获取到的地址
	"playUrl": "http://www.9rmb.com/play/{playUrl}.html",
    // 解析webview的user-agent
	"playUa": "",
    // 搜索地址
	"searchUrl": "http://www.9rmb.com/search?wd={wd}",
    // 同上面的homeVod字段 搜索结果中的视频信息
	"scVodNode": "//div[@class='movie-item']/a",
	"scVodName": "/@title",
	"scVodId": "/@href",
	"scVodIdR": "/show/(\\w+).html",
	"scVodImg": "/img/@src",
	"scVodMark": "/button/text()"
}
```