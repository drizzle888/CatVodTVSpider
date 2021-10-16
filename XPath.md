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
    "ext": "{\"ua\":\"\",\"homeUrl\":\"http://www.9rmb.com\",\"cateNode\":\"//ul[contains(@class,'navbar-nav')]/li/a[contains(@href, '.html') and not(contains(@href, '6'))]\",\"cateName\":\"/text()\",\"cateId\":\"/@href\",\"cateIdR\":\"/type/(\\\\d+).html\",\"cateManual\":{},\"homeVodNode\":\"//div[@class='col-md-12 movie-item-out']//a[not(contains(@href, '6'))]/parent::*/parent::*/parent::*/div[contains(@class, 'movie-item-out') and position()<10]/div[@class='movie-item']/a\",\"homeVodName\":\"/@title\",\"homeVodId\":\"/@href\",\"homeVodIdR\":\"/show/(\\\\w+).html\",\"homeVodImg\":\"/img/@src\",\"homeVodMark\":\"/button/text()\",\"cateUrl\":\"http://www.9rmb.com/type/{cateId}/{catePg}.html\",\"cateVodNode\":\"//div[@class='movie-item']/a\",\"cateVodName\":\"/@title\",\"cateVodId\":\"/@href\",\"cateVodIdR\":\"/show/(\\\\w+).html\",\"cateVodImg\":\"/img/@src\",\"cateVodMark\":\"/button/text()\",\"dtUrl\":\"http://www.9rmb.com/show/{vid}.html\",\"dtNode\":\"//div[@class='container-fluid']\",\"dtName\":\"//div[@class='col-md-9']//div[@class='col-md-4']//img/@alt\",\"dtNameR\":\"\",\"dtImg\":\"//div[@class='col-md-9']//div[@class='col-md-4']//img/@src\",\"dtImgR\":\"\",\"dtCate\":\"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '类型')]/parent::*/following-sibling::*/text()\",\"dtCateR\":\"\",\"dtYear\":\"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '日期')]/parent::*/following-sibling::*/text()\",\"dtYearR\":\"\",\"dtArea\":\"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '国家')]/parent::*/following-sibling::*/text()\",\"dtAreaR\":\"\",\"dtMark\":\"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '状态')]/parent::*/following-sibling::*/text()\",\"dtMarkR\":\"\",\"dtActor\":\"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '主演')]/parent::*/following-sibling::*/text()\",\"dtActorR\":\"\",\"dtDirector\":\"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '导演')]/parent::*/following-sibling::*/text()\",\"dtDirectorR\":\"\",\"dtDesc\":\"//p[@class='summary']/text()\",\"dtDescR\":\"\",\"dtFromNode\":\"//div[contains(@class,'resource-list')]/div[@class='panel-heading']/strong\",\"dtFromName\":\"/text()\",\"dtFromNameR\":\"\\\\S+\\\\.(\\\\S+) \\\\(\",\"dtUrlNode\":\"//div[contains(@class,'resource-list')]/ul[@class='dslist-group']\",\"dtUrlSubNode\":\"/li/a\",\"dtUrlId\":\"@href\",\"dtUrlIdR\":\"/play/(\\\\S+).html\",\"dtUrlName\":\"/text()\",\"dtUrlNameR\":\"\",\"playUrl\":\"http://www.9rmb.com/play/{playUrl}.html\",\"playUa\":\"\",\"searchUrl\":\"http://www.9rmb.com/search?wd={wd}\",\"scVodNode\":\"//div[@class='movie-item']/a\",\"scVodName\":\"/@title\",\"scVodId\":\"/@href\",\"scVodIdR\":\"/show/(\\\\w+).html\",\"scVodImg\":\"/img/@src\",\"scVodMark\":\"/button/text()\"}"
},
{
    "key": "csp_xpath_jpys",
    "name": "极品影视(XPath)",
    "type": 3,
    "api": "csp_XPath",
    "searchable": 1,
    "quickSearch": 1,
    "filterable": 1,
    "ext": "{\"ua\":\"\",\"homeUrl\":\"https://www.jpysvip.net\",\"cateNode\":\"//ul[contains(@class,'myui-header__menu')]/li[@class='dropdown-hover']//ul/li/a[contains(@href, 'vodtype') and not(contains(@href, '26'))]\",\"cateName\":\"/text()\",\"cateId\":\"/@href\",\"cateIdR\":\"/vodtype/(\\\\d+).html\",\"cateManual\":{},\"homeVodNode\":\"//div[contains(@class, 'col-lg-wide-75')]//ul[contains(@class,'myui-vodlist')]/li//a[contains(@class,'myui-vodlist__thumb')]\",\"homeVodName\":\"/@title\",\"homeVodId\":\"/@href\",\"homeVodIdR\":\"/voddetail/(\\\\w+).html\",\"homeVodImg\":\"@data-original\",\"homeVodImgR\":\"\\\\S+(http\\\\S+)\",\"homeVodMark\":\"/span[contains(@class,'pic-text')]/text()\",\"cateUrl\":\"https://www.jpysvip.net/vodtype/{cateId}-{catePg}.html\",\"cateVodNode\":\"//ul[contains(@class,'myui-vodlist')]//li//a[contains(@class,'myui-vodlist__thumb')]\",\"cateVodName\":\"/@title\",\"cateVodId\":\"/@href\",\"cateVodIdR\":\"/voddetail/(\\\\w+).html\",\"cateVodImg\":\"@data-original\",\"cateVodImgR\":\"\\\\S+(http\\\\S+)\",\"cateVodMark\":\"/span[contains(@class,'pic-text')]/text()\",\"dtUrl\":\"https://www.jpysvip.net/voddetail/{vid}.html\",\"dtNode\":\"//div[contains(@class,'col-lg-wide-75')]\",\"dtName\":\"//div[@class='myui-content__thumb']/a[contains(@class,'myui-vodlist__thumb')]/@title\",\"dtNameR\":\"\",\"dtImg\":\"//div[@class='myui-content__thumb']/a[contains(@class,'myui-vodlist__thumb')]/img/@data-original\",\"dtImgR\":\"\\\\S+(http\\\\S+)\",\"dtCate\":\"//div[@class='myui-content__detail']//span[contains(@class,'text-muted') and contains(text(), '分类')]/following-sibling::*/text()\",\"dtCateR\":\"\",\"dtYear\":\"//div[@class='myui-content__detail']//span[contains(@class,'text-muted') and contains(text(), '年份')]/following-sibling::*/text()\",\"dtYearR\":\"\",\"dtArea\":\"//div[@class='myui-content__detail']//span[contains(@class,'text-muted') and contains(text(), '地区')]/following-sibling::*/text()\",\"dtAreaR\":\"\",\"dtMark\":\"\",\"dtMarkR\":\"\",\"dtActor\":\"//div[@class='myui-content__detail']//span[contains(@class,'text-muted') and contains(text(), '主演')]/following-sibling::*/text()\",\"dtActorR\":\"\",\"dtDirector\":\"//div[@class='myui-content__detail']//span[contains(@class,'text-muted') and contains(text(), '导演')]/following-sibling::*/text()\",\"dtDirectorR\":\"\",\"dtDesc\":\"//div[@class='myui-content__detail']//span[contains(@class,'text-muted') and contains(text(), '简介')]/parent::text()\",\"dtDescR\":\"\",\"dtFromNode\":\"//a[@data-toggle='tab' and contains(@href, 'playlist')]\",\"dtFromName\":\"/text()\",\"dtFromNameR\":\"\",\"dtUrlNode\":\"//div[contains(@class,'tab-content')]/div[contains(@id, 'playlist')]\",\"dtUrlSubNode\":\"//li/a\",\"dtUrlId\":\"@href\",\"dtUrlIdR\":\"/vodplay/(\\\\S+).html\",\"dtUrlName\":\"/text()\",\"dtUrlNameR\":\"\",\"playUrl\":\"https://www.jpysvip.net/vodplay/{playUrl}.html\",\"playUa\":\"\",\"searchUrl\":\"https://www.jpysvip.net/index.php/ajax/suggest?mid=1&wd={wd}&limit=10\",\"scVodNode\":\"json:list\",\"scVodName\":\"name\",\"scVodId\":\"id\",\"scVodIdR\":\"\",\"scVodImg\":\"pic\",\"scVodMark\":\"\"}"
},
{
    "key": "csp_xpath_age",
    "name": "AGE动漫(XPath)",
    "type": 3,
    "api": "csp_XPath",
    "searchable": 1,
    "quickSearch": 1,
    "filterable": 1,
    "ext": "{\"ua\":\"\",\"homeUrl\":\"https://www.agefans.cc/update\",\"cateManual\":{\"全部\":\"\",\"中国\":\"-中国-all-all\",\"日本\":\"-日本-all-all\",\"欧美\":\"-欧美-all-all\"},\"homeVodNode\":\"//div[@class='blockcontent']//ul[@class='ul_li_a6']/li\",\"homeVodName\":\"/h4[@class='anime_icon2_name']/a/text()\",\"homeVodId\":\"/h4[@class='anime_icon2_name']/a/@href\",\"homeVodIdR\":\"/detail/(\\\\w+)\",\"homeVodImg\":\"//img/@src\",\"homeVodMark\":\"//img/@title\",\"cateUrl\":\"https://www.agefans.cc/catalog/all-all-all-all-all-time-{catePg}{cateId}\",\"cateVodNode\":\"//div[@class='blockcontent1']/div/a\",\"cateVodName\":\"/img/@alt\",\"cateVodId\":\"/@href\",\"cateVodIdR\":\"/detail/(\\\\w+)\",\"cateVodImg\":\"/img/@src\",\"cateVodMark\":\"/span[@class='newname']/text()\",\"dtUrl\":\"https://www.agefans.cc/detail/{vid}\",\"dtNode\":\"//div[@id='container']\",\"dtName\":\"//div[@class='div_left']/div[@class='baseblock'][1]/div[@class='blockcontent']/img/@alt\",\"dtImg\":\"//div[@class='div_left']/div[@class='baseblock'][1]/div[@class='blockcontent']/img/@src\",\"dtCate\":\"//div[@class='div_left']/div[@class='baseblock'][2]//span[@class='detail_imform_tag' and contains(text(), '剧情类型')]/following-sibling::*/text()\",\"dtYear\":\"//div[@class='div_left']/div[@class='baseblock'][2]//span[@class='detail_imform_tag' and contains(text(), '首播时间')]/following-sibling::*/text()\",\"dtArea\":\"//div[@class='div_left']/div[@class='baseblock'][2]//span[@class='detail_imform_tag' and contains(text(), '地区')]/following-sibling::*/text()\",\"dtMark\":\"\",\"dtDirector\":\"//div[@class='div_left']/div[@class='baseblock'][2]//span[@class='detail_imform_tag' and contains(text(), '制作公司')]/following-sibling::*/text()\",\"dtDesc\":\"//div[@class='div_right']/div[@class='baseblock'][2]//p/text()\",\"dtFromNode\":\"//div[@id='playlist-div']/ul[@class='menu0']/li\",\"dtFromName\":\"/text()\",\"dtUrlNode\":\"//div[@id='playlist-div']/div[@class='main0']/div\",\"dtUrlSubNode\":\"//li/a\",\"dtUrlId\":\"@href\",\"dtUrlIdR\":\"/play/(\\\\S+)\",\"dtUrlName\":\"/text()\",\"playUrl\":\"https://www.agefans.cc/play/{playUrl}\",\"playUa\":\"\",\"searchUrl\":\"https://www.agefans.cc/search?query={wd}&page=1\",\"scVodNode\":\"//div[@class='blockcontent1']/div/a\",\"scVodName\":\"/img/@alt\",\"scVodId\":\"/@href\",\"scVodIdR\":\"/detail/(\\\\w+)\",\"scVodImg\":\"/img/@src\",\"scVodMark\":\"/span[@class='newname']/text()\"}"
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
    // 同上面的homeVod字段 搜索结果中的视频信息, 这里有对苹果cms suggest搜索接口的特殊支持，参考示例中的极品影视
	"scVodNode": "//div[@class='movie-item']/a",
	"scVodName": "/@title",
	"scVodId": "/@href",
	"scVodIdR": "/show/(\\w+).html",
	"scVodImg": "/img/@src",
	"scVodMark": "/button/text()"
}
```
