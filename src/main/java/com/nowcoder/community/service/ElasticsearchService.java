package com.nowcoder.community.service;

import com.nowcoder.community.pojo.DiscussPost;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ElasticsearchService {

    @Autowired
    private ElasticsearchRepository elasticsearchRepository;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void saveDiscussPost(DiscussPost post) {
        elasticsearchRepository.save(post);
    }
    public void deleteDiscussPost(int id) {
        elasticsearchRepository.deleteById(id);
    }


    public SearchPage<DiscussPost> searchDiscussPost(String keyword, int current, int limit) {
        NativeSearchQuery searchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(keyword, "title", "content"))
                .withSorts(SortBuilders.fieldSort("type").order(SortOrder.DESC),
                        (SortBuilders.fieldSort("score").order(SortOrder.DESC)),
                        (SortBuilders.fieldSort("createTime").order(SortOrder.DESC)))
                .withPageable(PageRequest.of(current, limit))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();

        //得到查询结果
        SearchHits<DiscussPost> search = elasticsearchRestTemplate.search(searchQueryBuilder, DiscussPost.class);
        //将其结果返回并进行分页
        SearchPage<DiscussPost> page = SearchHitSupport.searchPageFor(search, Page.empty().getPageable());

        if (!page.isEmpty()) {
            for (SearchHit<DiscussPost> discussPostSearch : page) {
                DiscussPost discussPost = discussPostSearch.getContent();
                //取高亮
                List<String> title = discussPostSearch.getHighlightFields().get("title");
                if(title!=null){
                    discussPost.setTitle(title.get(0));
                }
                List<String> content = discussPostSearch.getHighlightFields().get("content");
                if(content!=null){
                    discussPost.setContent(content.get(0));
                }
            }
        }

        return page;
    }
}
