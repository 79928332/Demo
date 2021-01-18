package com.hxzy.util.autoservice;

import java.io.*;

/**
 * 把模板变为实际类
 */
public class AutoTempalteClass {

    //生成的代码存储路径 D:\xiangmu\src\main\java\com\hxzy\service  D:\xiangmu\src\main\java\com\hxzy\service\impl
    private static final String servicePath="E:\\demo01\\src\\main\\java\\com\\hxzy\\service";
    private static final String serviceImplPath="E:\\demo01\\src\\main\\java\\com\\hxzy\\service\\impl";

    //模板文件的路径
    private static final String templateServicePath="E:\\demo01\\src\\main\\java\\com\\hxzy\\util\\autoservice\\template\\TemplateService.txt";
    private static final String templateServiceImplPath="E:\\demo01\\src\\main\\java\\com\\hxzy\\util\\autoservice\\template\\TemplateServiceImpl.txt";

    //java实体类名称(使用者只需要关心这个名称)
    private   String ENTITY_NBAME="Dept";
    //数据库表主键的类型
    private   String PRIMARY_KEY_TYPE="Integer";

    /**
     * 自动生成类
     * @param ENTITY_NBAME  JAVA实体类名
     * @param PRIMARY_KEY_TYPE  数据库表主键类型
     */
    public AutoTempalteClass(String ENTITY_NBAME, String PRIMARY_KEY_TYPE) {
        this.ENTITY_NBAME = ENTITY_NBAME;
        this.PRIMARY_KEY_TYPE = PRIMARY_KEY_TYPE;
    }

    public void create() throws InterruptedException {
       //线程1
       Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    createService();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //线程2
        Thread t2= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    createServiceImpl();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //线程1启动
        t1.start();
        //线程2启动
        t2.start();
        //主线程等子线程做完以后，才执行主线程的输出操作
        t1.join();
        t2.join();

        System.out.println("代码创建完毕-----");


    }

    //读取TemplateService.txt的代码生成java接口
    private void createService() throws IOException {
       //一行一行的读
        BufferedReader  br=new BufferedReader(new FileReader(new File(templateServicePath)));
        StringBuffer  sb=new StringBuffer();
        String str="";
        while(  (str=br.readLine())!=null){
          sb.append(str).append("\r\n");
        }
        br.close();
        //替换里面的内容
        String  content=sb.toString().replace("${1}",ENTITY_NBAME).replace("${2}",PRIMARY_KEY_TYPE);

        //写入指定的内容
        String fileName=ENTITY_NBAME+"Service.java";
        File saveFile=new File(servicePath,fileName);
        //指定文件的编码格式
        BufferedWriter  bw=new BufferedWriter( new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(saveFile)),"UTF-8") );
        bw.write(content);
        bw.close();

    }

    //读取TemplateServiceImpl.txt的代码生成java实现类
    private  void createServiceImpl() throws IOException {
        BufferedReader  br=new BufferedReader(new FileReader(new File(templateServiceImplPath)));
        StringBuffer  sb=new StringBuffer();
        String str="";
        while(  (str=br.readLine())!=null){
            sb.append(str).append("\r\n");
        }
        br.close();
        //替换里面的内容
        String lowerEntityName= ENTITY_NBAME.substring(0,1).toLowerCase()+ ENTITY_NBAME.substring(1);
        String  content=sb.toString().replace("${1}",ENTITY_NBAME).replace("${2}",PRIMARY_KEY_TYPE).replace("${3}",lowerEntityName);
        //写入指定的内容
        String fileName=ENTITY_NBAME+"ServiceImpl.java";
        File saveFile=new File(serviceImplPath,fileName);
        //指定文件的编码格式
        BufferedWriter  bw=new BufferedWriter( new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(saveFile)),"UTF-8") );
        bw.write(content);
        bw.close();
    }



}
