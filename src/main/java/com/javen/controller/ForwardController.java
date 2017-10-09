package com.javen.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller  
public class ForwardController {  
	private Logger log=LoggerFactory.getLogger(this.getClass());
    
    //管理登陆后跳转页面
    @RequestMapping(value = "/forward/xinzeng.do", produces = "text/html; charset=utf-8")  
    public ModelAndView addNew() {  
    	log.info("进入管理登陆跳转页面控制器　admin-xzda-qz.html");
        ModelAndView view = new ModelAndView("admin-xzda-qz");  
        return view;
    }
    //管理员跳转非分还原
    @RequestMapping(value = "/forward/adBfHyLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView adBfHyLink() {  
    	log.info("进入管理登陆跳转 面控制器　adm_bfhy.html");
        ModelAndView view = new ModelAndView("adm_bfhy");  
        return view;
    }
  //管理员跳转系统日志
    @RequestMapping(value = "/forward/adXtrzLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView adXtrzLink() {  
    	log.info("进入管理登陆跳转系统日子制控器　adm_xzrz.html");
        ModelAndView view = new ModelAndView("adm_xzrz");  
        return view;
    }
    //跳转到注册页面
    @RequestMapping(value = "/forward/zhuce.do", produces = "text/html; charset=utf-8")  
    public ModelAndView zhuce() {  
    	log.info("进入跳转注册子空制器　regist.html");
        ModelAndView view = new ModelAndView("regist");  
        return view;
    }
  //跳转到管理人员
    @RequestMapping(value = "/forward/adGlRyLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView adGlRy() {  
    	log.info("进入跳转跳转到管理人员控制器　ad_gl_ry.html");
        ModelAndView view = new ModelAndView("ad_gl_ry");  
        return view;
    }
    //新增全宗跳转页面
    @RequestMapping(value = "/forward/adXzQzLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView addQz() {  
    	log.info("进入新增全宗业务跳转控制器  ad_xz+qz.hmt");
        ModelAndView view = new ModelAndView("ad_xz_qz");  
        return view;
    }
    
    @RequestMapping(value = "/forward/adAside.do", produces = "text/html; charset=utf-8")  
    public ModelAndView addAside() {  
    	log.info("未解控制器");
        ModelAndView view = new ModelAndView("ad_aside");  
        return view;
    }
    
    //增加页面头部的跳转
    @RequestMapping(value = "/forward/adHead.do", produces = "text/html; charset=utf-8")  
    public ModelAndView addHead() {  
    	log.info("进入增加面头部跳转控制器 ad_head.html");
        ModelAndView view = new ModelAndView("ad_head");  
        return view;
    }
    
    //增加页面尾部的跳转
    @RequestMapping(value = "/forward/adFooter.do", produces = "text/html; charset=utf-8")  
    public ModelAndView addFooter() {  
    	log.info("进入新增页面尾部的跳转孔子器    ad_footer.html");
        ModelAndView view = new ModelAndView("ad_footer");  
        return view;
    }
    
    //新增案卷页面的跳转
    @RequestMapping(value = "/forward/adXzAjLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView addAj() {  
    	log.info("进入新增案卷也面对额跳转控制器啊   ad_xz_aj.html  ");
        ModelAndView view = new ModelAndView("ad_xz_aj");  
        return view;
    }
    
    //管理案卷页面跳转
    @RequestMapping(value = "/forward/adGlAjLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView glAj() {  
    	log.info("进入管理案卷页面跳转控制器   ad_gl_aj.html");
        ModelAndView view = new ModelAndView("ad_gl_aj");  
        return view;
    }
    
    //管理全宗页面跳转
    @RequestMapping(value = "/forward/adGlQzLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView glQz() {  
    	log.info("进入管理全宗页面跳转控制器   ad_gl_qz.html");
        ModelAndView view = new ModelAndView("ad_gl_qz");  
        return view;
    }
    
    //管理卷内页面跳转
    @RequestMapping(value = "/forward/adGlJnLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView glJn() {  
    	log.info("进入管理卷内页面跳转控制器   ad_gl_jn.html");
        ModelAndView view = new ModelAndView("ad_gl_jn");  
        return view;
    }
    
    //管理归档页面跳转
    @RequestMapping(value = "/forward/adGlGdLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView glGd() {  
    	log.info("进入管理归档页面跳转控制器    ad_gl_gd.html");
        ModelAndView view = new ModelAndView("ad_gl_gd");  
        return view;
    }
    
    //新增卷内页面跳转
    @RequestMapping(value = "/forward/adXzJnLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView addJn() {  
    	log.info("进入新增卷内页面跳转控制器    ad_xz_jn.html");
        ModelAndView view = new ModelAndView("ad_xz_jn");  
        return view;
    }
    
    //新增归档页面跳转
    @RequestMapping(value = "/forward/adXzGdLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView addGd() {  
    	log.info("进入新增归档页面跳转控制器    ad_xz_gd.html");
        ModelAndView view = new ModelAndView("ad_xz_gd");  
        return view;
    }
    //接收档案页面
    @RequestMapping(value = "/forward/admDaJsLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView adDajsLink() {  
    	log.info("进入接收档案页面控制器    adm_dajs.html");
        ModelAndView view = new ModelAndView("adm_dajs");  
        return view;
    }
    //提交档案（移交档案）
    @RequestMapping(value = "/forward/admDaTjLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView adDaTjLink() {  
    	log.info("进入提交档案跳转控制器   adm_datj.html");
        ModelAndView view = new ModelAndView("adm_datj");  
        return view;
    }
    //登陆页面跳转
    @RequestMapping(value = "/forward/adIndex.do", produces = "text/html; charset=utf-8")  
    public ModelAndView adIndex() {  
    	log.info("进入登陆页面跳转控制器     ad_index.html");
        ModelAndView view = new ModelAndView("ad_index");  
        return view;
    }
    //index页面跳转
    @RequestMapping(value = "/forward/login.do", produces = "text/html; charset=utf-8")  
    public ModelAndView Login() {  
    	log.info("进入index页面跳转控制器    login.html");
        ModelAndView view = new ModelAndView("login");  
        return view;
    }
    
    @RequestMapping(value = "/forward/adminMain.do", produces = "text/html; charset=utf-8")  
    public ModelAndView adminMain() {  
    	log.info("进入管理主页跳转控制器   admin.html");
        ModelAndView view = new ModelAndView("admin");  
        return view;
    }
    
    @RequestMapping(value = "/forward/adSjdrLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView adXtrzLink1() {  
    	log.info("进入数据导入反转控制器 adm_sjdr.html");
        ModelAndView view = new ModelAndView("adm_sjdr");  
        return view;
    }
    
    @RequestMapping(value = "/forward/adXtszLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView adXtszLink() {  
    	log.info("进入数据导入反转控制器 adm_xtsz.html");
        ModelAndView view = new ModelAndView("adm_xtsz");  
        return view;
    }
    
    @RequestMapping(value = "/forward/adSjfxLink.do", produces = "text/html; charset=utf-8")  
    public ModelAndView adSjfxLink() {  
    	log.info("进入数据导入反转控制器 adm_sjfx.html");
        ModelAndView view = new ModelAndView("adm_sjfx");  
        return view;
    }
}  
    