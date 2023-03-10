package com.nowcoder.community;


import com.alibaba.fastjson2.JSONObject;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.elasticsearch.DiscussPostRepository;
import com.nowcoder.community.pojo.DiscussPost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class ElasticsearchTests {

    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private DiscussPostRepository discussPostRepository;
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testInsert() {
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(241));
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(242));
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(243));
    }

    @Test
    public void testInsertList() {
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(101, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(102, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(103, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(111, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(112, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(131, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(132, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(133, 0, 100, 0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPosts(134, 0, 100, 0));
    }

    @Test
    public void testUpdate() {
        DiscussPost discussPost = discussPostMapper.selectDiscussPostById(231);
        discussPost.setContent("???????????????????????????");
        discussPostRepository.save(discussPost);
    }

    @Test
    public void testDelete() {
        // discussPostRepository.deleteById(231);
        discussPostRepository.deleteAll();
    }

//    @Test
//    public void testSearchByRepository() {
////        NativeSearchQuery searchQuery =
//    }
//
//    @Test
//    public void highlightQuery() throws Exception {
//        //1.?????????????????? searchRequest
//        SearchRequest searchRequest = new SearchRequest("discusspost");//discusspost???????????????????????????
//        // 2.???????????? HighlightBuilder
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("title");
//        //?????????????????????????????????????????????
//        highlightBuilder.field("content");
//        highlightBuilder.requireFieldMatch(false);
//        highlightBuilder.preTags("<span style='color:red'>"); //??????????????????????????????html??????  ???????????????????????????????????????
//        highlightBuilder.postTags("</span>");
//        // 3.?????????????????? searchSourceBuilder
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
//                .query(QueryBuilders.multiMatchQuery("???????????????", "title", "content"))
//                .sort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
//                .sort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
//                .sort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
//                .from(0)// ???????????????????????????
//                .size(10)// ??????????????????????????????
//                .highlighter(highlightBuilder);//????????????
//        // 4.???????????????????????????????????????
//        searchRequest.source(searchSourceBuilder);
//        // 5.???????????????????????????
//        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//        List<DiscussPost> list = new LinkedList<>();
//        for (SearchHit hit : searchResponse.getHits().getHits()) {
//            DiscussPost discussPost = JSONObject.parseObject(hit.getSourceAsString(), DiscussPost.class);
//            // ???????????????????????????
//            HighlightField titleField = hit.getHighlightFields().get("title");
//            if (titleField != null) {
//                discussPost.setTitle(titleField.getFragments()[0].toString());  //title=<span style='color:red'>?????????</span>??????????????????...  }
//                HighlightField contentField = hit.getHighlightFields().get("content");
//                if (contentField != null) {
//                    discussPost.setContent(contentField.getFragments()[0].toString());  //content=??????????????????<span style='color:red'>?????????</span>????????????...  }
//                    System.out.println(discussPost);
//                    list.add(discussPost);
//                }
//            }
//        }
//    }
}
