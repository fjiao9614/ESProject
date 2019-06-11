import com.fasterxml.jackson.core.JsonPointer;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class ESExample{
    public static void main(String[] args)throws Exception{
        RestClient restClient = RestClient.builder(
                new HttpHost("106.14.14.144", 9200, "http"),
                new HttpHost("47.100.43.254", 9200, "http"),
                new HttpHost("47.102.157.3", 9200, "http")).build();

//        Request request = new Request("PUT", "index_name/_doc/1");
//        (/{index}/_doc/{id}, /{index}/_doc, or /{index}/_create/{id})."]
//        request.setJsonEntity("{\"key\":\"ElasticSearch\",\"url\": \"https://blog.csdn.net/qq_15958689/article/details/79524291\",\"title\": \"ES（ElasticSearch） 索引创建\",\"abstract\": \"虽然es可以自己创建索引(直接在对应的索引index和Type中插入数据,es会根据插入的数据自己匹配类型),但是es自己创建的索引,对于text的类型的都是进行st...\", \"content\": \"2、索引设置 ES 默认提供了好多索引配置选项，参考https://www.elastic.co/guide/en/elasticsearch/reference/5.6/index-modules.html，这些配置选项都有经过优化的默认配置值，除非你非常清楚这些配置的作用以及知道为什么去修改它，不然使用其默认值即可。\"}");


//        将类型 index_type 替换成 _doc
//        Request request = new Request("GET", "index_name/_doc/1");


        Request request = new Request("POST", "index_name/_update/1");
//      endpoint: {index}/_update/{id}
        request.setJsonEntity("{\"doc\":{\"title\": \"更新ES（ElasticSearch） 索引创建\"}}\n");


        Response response = restClient.performRequest(request);
        RequestLine requestLine = response.getRequestLine();
        System.out.println(requestLine);
        HttpHost host = response.getHost();
        System.out.println(host);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        Header[] headers = response.getHeaders();
        for(int i = 0; i < headers.length; i++){
            System.out.println(headers[i]);
        }
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println(responseBody);
        restClient.close();
    }

}
