package com.soga;

import com.alibaba.fastjson.JSON;
import com.soga.entity.Stu;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class SogaEsApiApplicationTests {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
    }

    //创建索引
    @Test
    void create() throws Exception {
        CreateIndexRequest request = new CreateIndexRequest("jd_goods");
        CreateIndexResponse createIndexResponse =
                client.indices().
                        create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    //判断索引是否存在
    @Test
    void existIndex() throws Exception {
        GetIndexRequest request = new GetIndexRequest("jd_goods");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //删除索引
    @Test
    void delete() throws Exception {
        DeleteIndexRequest request = new DeleteIndexRequest("testxl");
        AcknowledgedResponse delete = client.indices().
                delete(request, RequestOptions.DEFAULT);
        System.out.println(delete);
    }

    //创建文档
    @Test
    void createDoc() throws IOException {
        Stu stu = new Stu("1005", 55, "xl", 38.8, "ccc is a new ccc", "this is a ccc , a real ccc");

        IndexRequest request = new IndexRequest("jd_goods");

        request.id("1");
        request.timeout("1s");

        //对象转换json
        request.source(JSON.toJSONString(stu), XContentType.JSON);

        //发送请求
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());
        System.out.println(response.status());

    }

    //判断文档是否存在
    @Test
    void getExistDoc() throws IOException {
        GetRequest getRequest = new GetRequest("study_lx", "1");
        //不获取返回的_source 上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        //发送请求
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);

        System.out.println(exists);

    }


    //获取文档
    @Test
    void getDoc() throws IOException {
        GetRequest getRequest = new GetRequest("study_lx", "1");

        //发送请求
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        System.out.println(getResponse.getSourceAsString());
        System.out.println(getResponse.toString());

    }

    //更新文档
    @Test
    void UpdateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("study_lx", "1");

        Stu stu = new Stu();
        stu.setAge(77);
        updateRequest.doc(JSON.toJSONString(stu), XContentType.JSON);
        //发送请求
        UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);

        System.out.println(update.toString());

    }

    //删除文档
    @Test
    void deleteDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("study_lx", "1");
        deleteRequest.timeout("1s");

        DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete.toString());

    }

    //批量插入
    @Test
    void createBatchDoc() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        ArrayList<Stu> stuList = new ArrayList<>();
        stuList.add(new Stu("xi1", 3, "ces", 12.2, "1", "1"));
        stuList.add(new Stu("xi2", 4, "ces", 12.2, "1", "1"));
        stuList.add(new Stu("xi3", 5, "ces", 12.2, "1", "1"));
        stuList.add(new Stu("xi4", 6, "ces", 12.2, "1", "1"));
        stuList.add(new Stu("xi5", 7, "ces", 12.2, "1", "1"));

        for (int i = 0; i < stuList.size(); i++) {
            bulkRequest.add(new IndexRequest("study_lx").id("" + (i + 1)).
                    source(JSON.toJSONString(stuList.get(i)), XContentType.JSON));
        }

        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());

    }


    //多条件封装查询
    //SearchRequest搜索请求
    // SearchSourceBuilder条件构选
    // HighLightBuiLder构建高亮
    // TermQueryBuiLder精确查询
    // MatchALLQueryBuilder
    //QueryBuilder对应我们刚才看到的命令!
    @Test
    void searchDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("study_lx");//构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //查询条件，我们可以使用QueryBuiLders 工具来实现
        //QueryBuiLders.termQuery精确
        //QueryBuiLders.matchAlLQuery()匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "ces");
        //MatchAlLQueryBuiLder matchALLQueryBuiLder = QueryBuilders.matchALLQuery();
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("=================================");
        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsMap());
        }


    }

}
