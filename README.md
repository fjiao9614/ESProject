### ESProject
#### 1. 数据示例
```json
{"key": "特朗普", 
 "url": "http://www.xinhuanet.com/world/2017tlpfh/", 
 "title": "特朗普访华_新华国际_新华网", 
 "abstract": "习近平为美国总统特朗普举行欢迎仪式 习近平同美国总统特朗普举行会谈 习近平和夫人彭丽媛陪同美国总统特朗普和夫人梅拉尼娅参观故宫博物院  习近平和夫人彭丽媛同美国总统特...", 
 "content": "首页焦点新闻最新播报深度透视视频高清大图EnglishEnglishEspañolFrançaisРусскийязык日本語عربي한국어DeutschPortuguês-->中美关系新篇章——北京会晤集锦习近平为美国总统特朗普举行欢迎仪式习近平同美国总统特朗普举行会谈习近平和夫人彭丽媛",
 "engine": "百度"}
```

#### 2. KafkaProducerES.java 
包括Kafka集群的连接  

从txt读取数据，生产到Kafka中
#### 3. KafkaConsumerES 
包括 Kafka、ES 集群的连接

将Kafka生产的数据通过消费者取出，插入到ES中