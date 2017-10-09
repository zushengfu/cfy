package com.javen.function;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.javen.model.User;


public class LuceneSpare {

    private Directory dir=null;
    private BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();

    //创建索引对象的条件
    private IndexWriter getWriter()throws Exception{
        /**
         * 生成的索引我放在了C盘，可以根据自己的需要放在具体位置
         */
        dir= FSDirectory.open(Paths.get("F://lucene"));
        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
        IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
        IndexWriter writer=new IndexWriter(dir, iwc);
        return writer;
    }
    //添加索引对象
    public void addIndex(String filePath)throws Exception{
        IndexWriter writer=this.getWriter();
        Document doc=new Document();
        doc.add(new StringField("filePath",filePath, Field.Store.YES));
        /**
         * yes是会将数据存进索引，如果查询结果中需要将记录显示出来就要存进去，如果查询结果
         * 只是显示标题之类的就可以不用存，而且内容过长不建议存进去
         * 使用TextField类是可以用于查询的。
         */
        doc.add(new TextField("fileName", filePath.substring(filePath.lastIndexOf("/")+1,filePath.lastIndexOf(".")+1), Field.Store.YES));
        doc.add(new TextField("context",fileReaderAlone(filePath, "utf-8"), Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }
    //查询结果为map集
    public Map<String,String> query(String sear,String fileName) throws IOException, ParseException{
    	Map<String, String> map=new HashMap<String, String>();
    	dir= FSDirectory.open(Paths.get("F://lucene"));
    	DirectoryReader ireader = DirectoryReader.open(dir);
    	IndexSearcher isearcher = new IndexSearcher(ireader);
    	SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
		QueryParser parser = new QueryParser("context",analyzer);
		Query query = parser.parse(sear);
		booleanQuery.add(query,Occur.SHOULD);
		TopDocs hits=isearcher.search(booleanQuery.build(), 100);
		 QueryScorer scorer=new QueryScorer(query);
		 Document hitDoc;
		 ScoreDoc[] hitses= isearcher.search(query, null, 1000).scoreDocs;
		 for (int i = 0; i < hitses.length; i++) {
             hitDoc = isearcher.doc(hitses[i].doc);
             System.out.println("____________________________");
             System.out.println(hitDoc.get("filename"));
             System.out.println(hitDoc.get("content"));
             System.out.println(hitDoc.get("path"));
             System.out.println("____________________________");
             map.put("filename", hitDoc.get("filename"));
         }
         ireader.close();
         dir.close();
         return map;
         }
    //修改索引
    public void updateIndex(User user)throws Exception{
        IndexWriter writer=getWriter();
        Document doc=new Document();
        doc.add(new StringField("id",String.valueOf(user.getId()), Field.Store.YES));
        doc.add(new TextField("username", user.getUserName(), Field.Store.YES));
        doc.add(new TextField("description",user.getaddress(), Field.Store.YES));
        writer.updateDocument(new Term("id", String.valueOf(user.getId())), doc);
        writer.close();
    }
    //删除索引
   public void deleteIndex(String userId)throws Exception{
        IndexWriter writer=getWriter();
        writer.deleteDocuments(new Term("id", userId));
        writer.forceMergeDeletes(); // 强制删除
        writer.commit();
        writer.close();
    }

   //将单个文件读成流
    public   static String fileReaderAlone(String FileName, String charset)   
            throws  IOException  {   
        BufferedReader reader =   new  BufferedReader( new InputStreamReader(   
                new  FileInputStream(FileName), charset));   
        String line =   new  String();   
        String temp =   new  String();   
           
        while  ((line  =  reader.readLine())  !=   null)  {   
            temp +=  line;   
        }   
        reader.close();   
        return  temp;   
    }
    public static void main(String[] args) throws Exception {
    	LuceneSpare ls=new LuceneSpare();
    	ls.getWriter();
    	ls.addIndex("C:\\sam");
	}
}
	  
	