package com.javen.function;


import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.javen.model.User;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * 博客索引类
 * @author Administrator
 *
 */
public class LuceneIndex {

    private Directory dir=null;

    /**
     * 获取IndexWriter实例
     * @return
     * @throws Exception
     */
    public IndexWriter getWriter()throws Exception{
        /**
         * 生成的索引我放在了C盘，可以根据自己的需要放在具体位置
         */
        dir= FSDirectory.open(Paths.get("C://lucene"));
        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
        IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
        IndexWriter writer=new IndexWriter(dir, iwc);
        return writer;
    }

    /**
     * 添加博客索引
     * @param user
     */
    public void addIndex(User user)throws Exception{
        IndexWriter writer=getWriter();
        Document doc=new Document();
        doc.add(new StringField("id",String.valueOf(user.getId()), Field.Store.YES));
        /**
         * yes是会将数据存进索引，如果查询结果中需要将记录显示出来就要存进去，如果查询结果
         * 只是显示标题之类的就可以不用存，而且内容过长不建议存进去
         * 使用TextField类是可以用于查询的。
         */
        doc.add(new TextField("username", user.getUserName(), Field.Store.YES));
        doc.add(new TextField("description",user.getCardNumber(), Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }

    /**
     * 更新博客索引
     * @param user
     * @throws Exception
     */
    public void updateIndex(User user)throws Exception{
        IndexWriter writer=getWriter();
        Document doc=new Document();
        doc.add(new StringField("id",String.valueOf(user.getId()), Field.Store.YES));
        doc.add(new TextField("username", user.getUserName(), Field.Store.YES));
        doc.add(new TextField("description",user.getCardNumber(), Field.Store.YES));
        writer.updateDocument(new Term("id", String.valueOf(user.getId())), doc);
        writer.close();
    }

    /**
     * 删除指定博客的索引
     * @param userId
     * @throws Exception
     */
    public void deleteIndex(String userId)throws Exception{
        IndexWriter writer=getWriter();
        writer.deleteDocuments(new Term("id", userId));
        writer.forceMergeDeletes(); // 强制删除
        writer.commit();
        writer.close();
    }

    /**
     * 查询用户
     * @param q 查询关键字
     * @return
     * @throws Exception
     */
   
    
}