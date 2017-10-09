package com.javen.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import com.javen.dao.FileRecordsDao;
import com.javen.dao.FileRecordsPictureDao;
import com.javen.model.FileRecords;
import com.javen.model.FileRecordsPicture;


public class LuceneSearchFile {

    public static BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
    
    //创建索引
    protected static IndexWriter getIndexWriter(String filePath) throws IOException, ParseException{
    	Directory dir= FSDirectory.open(Paths.get("F://lucene"));//创建要存储成lucene的字典
        SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();//创建编辑器
        IndexWriterConfig iwc=new IndexWriterConfig(analyzer);//创建写入索引时的配置
        IndexWriter writer=new IndexWriter(dir, iwc);//创建索引
        File file=new File(filePath);
        File[] tempList = file.listFiles();
        System.out.println(tempList.length);
        for(File fil:tempList){//遍历所需要创建索引的文件
            Document doc=new Document();//创建文档
        	String address=fil.getAbsolutePath();//找到该文件的地址
        	String text=fileReaderAlone(address, "GBK");//读取地址文件成流（gbk）根据文件内容设定
        	System.out.println(address);
        	doc.add(new StringField("fileName",address.substring(address.lastIndexOf("\\")+1),Field.Store.YES));//将改文件名存在文档中，并设置成持久化
        	doc.add(new StringField("filePath",address, Field.Store.YES));//将改文件地址存在文档中，并设置成持久化
        	doc.add(new TextField("content",text,Field.Store.YES));//将改文件内容保存文档中，并设置持久化
        	writer.addDocument(doc);//用索引器将该文档加在索引中
        }
			writer.close();
			return writer;
    }
    		//根据条件找到fileRecords的list集合
			public  List<FileRecords>  getSearchResult(FileRecordsDao fileRecordsDao,FileRecordsPictureDao fileRecordsPictureDao,HttpServletRequest request,String conditions) throws IOException, ParseException{
			
			Directory dir= FSDirectory.open(Paths.get("F://lucene"));//打开存有需要索引的文件内容的字典器
			SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();//创建读写器
//			DirectoryReader ireader = DirectoryReader.open(dir);
			IndexSearcher isearcher = new IndexSearcher(DirectoryReader.open(dir));//创建读取索引器
			QueryParser parser = new QueryParser("content",analyzer);//创建查询器
			Query query = parser.parse(conditions);//查询器将要查询的条件进行整理
//			booleanQuery.add(query,Occur.SHOULD);//必须记住，联合查询！！！！！！！！
			TopDocs hits=isearcher.search(query, 100);//利用整理后的查询语句进行查询，设置最多显示100条数据
			ScoreDoc[] hitses=hits.scoreDocs;//得到查询数据的集合
			Document hitDoc;//创建文档
			System.out.println(hitses.length);
			List<Map<String,String>> list=new ArrayList<Map<String,String>>();
			List<FileRecords> l=new ArrayList<FileRecords>();
			for (int i = 0; i < hitses.length; i++) {
				 Map<String, String> map=new HashMap<String, String>();
	             hitDoc = isearcher.doc(hitses[i].doc);//将数据集合中的整个文档取出
	             System.out.println(hitDoc.get("fileName"));
	             String fileName=hitDoc.get("fileName");//提出存储在文档中的字段
	             String filePath="f:\\AcceptanceFile\\"+fileName.substring(0,fileName.indexOf("-"))+"\\"+fileName.substring(0,fileName.indexOf("-",fileName.indexOf("-")+1))+"\\"+fileName.substring(0,fileName.indexOf("-",fileName.indexOf("-",fileName.indexOf("-")+1)+1))+"/"+fileName.substring(0,fileName.indexOf("."))+".jpg";
	             System.out.println(filePath);
	             FileRecordsPicture fileRecordsPicture=new FileRecordsPicture();
				 FileRecords fileRecords=new FileRecords();
				 fileRecordsPicture=fileRecordsPictureDao.getAllFileRecordsPictureByFilePath(filePath);
				 if(fileRecordsPicture!=null){
				  	 fileRecords.setFileNum(fileRecordsPicture.getFileNum());
					 fileRecords=fileRecordsDao.getEntity(fileRecords);
						 if(fileRecords!=null&&l.size()==0){
							 l.add(fileRecords);
						 }
						 if(fileRecords!=null&&l.get(0).getCaseNum().equals(fileRecords.getCaseNum())==false){
							 l.add(fileRecords);
						 }
				 }
	         }
			System.out.println(list.toString());
//			ireader.close();
//			isearcher.getTopReaderContext();
			
	        dir.close();
	        return l;
    }
			
			 //将单个文件读成流
		    protected static String fileReaderAlone(String FileName, String charset)   
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
		    
		    
//			//删除索引
//			public static void deleteIndex(String userId)throws Exception{
//		        IndexWriter writer=getIndexWriter();
//		        writer.deleteDocuments(new Term("fileName", userId));
//		        writer.forceMergeDeletes(); // 强制删除
//		        writer.commit();
//		        writer.close();
//		    }
//		    public static void main(String[] args) throws IOException, ParseException {
//				getIndexWriter("F:\\AcceptanceFile\\付祖生");
//			}
}
	  
	