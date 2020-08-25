import java.io.*;
import java.util.Scanner;

public class IOTest {

    public static void main(String[] args) {

//        InputRead();
//        OutputWrite();

//        copyFileTest();

        readerTest();
    }


    public static void readerTest(){

        String path="file/article.txt";
        File file = new File(path);
        try {
            int ch;
            FileReader fileReader = new FileReader(file);
            while((ch=fileReader.read())!=-1){
                System.out.print((char)ch);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void writerTest() throws IOException {
        FileReader fr = new FileReader("a.java");
        char[] chs = new char[5]; //创建字符数组对象，定义数组长度为五（一次读五个数据）
        int len;
        while ((len=fr.read(chs))!=-1) {
//            System.out.println(new String(chs)); //每次读5个字符，如果最后一个字符不足五个，（后面的数组覆盖之前数组）如：最后数组分3个字符，会显示3个字符+之前数组的最后两字符
            System.out.println(new String(chs,0,len));
            System.out.println("len:" + len);
        }
        fr.close();

        //String(char[] value):把字符数组的数据封装成字符串对象
        String s = new String(chs); //方法二、通过String类的构造方法把数组封装成字符串对象
        System.out.println(s);

        System.out.println(new String(chs)); //使用方法二的匿名对象

    }

    public static void copyDemo() throws IOException {

        //一次读写一个字符数组
        FileReader fr = new FileReader("file/article.txt");
        FileWriter fw = new FileWriter("file/article2.txt");
        char[] chs = new char[5];

        int len;
        while ((len=fr.read(chs))!=-1) {
            fw.write(chs,0,len);
            fw.flush();
        }
        fw.close();
        fr.close();
    }




    public static void pathTest(){

        String path = "image/img1.jpg";
        File temp = new File(path);
        try {
            System.out.println(temp.getCanonicalPath());
            System.out.println(temp.getAbsolutePath());
            temp.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void InputStreamTest(){

        //将指定文件的内容读到控制台

        String path="D:/faker.txt";
        File file = new File(path);

        try{

            FileInputStream in = new FileInputStream(file);
//            System.out.println(in.available());
            //方法一：一次读完
//            byte[] by = new byte[in.available()];
//            in.read(by);
//            System.out.println(new String(by));


            //方法二：分多次读文件
            byte[] by2 = new byte[3];
            int lenth;
            while((lenth=in.read(by2))!=-1){
                System.out.print(new String(by2,0,lenth));
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void OutputStreamTest(){

        //将控制台的输入存入指定文件
        System.out.println("请输入：");
        Scanner scan = new Scanner(System.in);
        String next = scan.next();
        byte[] bytes = next.getBytes();

        String path="D:/article.txt";
        File file = new File(path);
        FileOutputStream out = null;

        try {
            if(!file.exists()){
                file.createNewFile();
            }

            //方式一：从文件头部写入，即覆盖写
            //FileOutputStream out = new FileOutputStream(file);

            //方式二：从文件末尾写入
            out = new FileOutputStream(file,true);
            out.write(bytes);
            System.out.println("已写入");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void copyFileTest() {

        BufferedInputStream buf;

        long start = System.currentTimeMillis();


        String src1 = "image/img1.jpg";
        String src2 = "image/img2.jpg";
        copyFile(src1,src2);

        long end = System.currentTimeMillis();

        System.out.println("复制操作花费的时间为： " + (end-start));
    }


    private static void copyFile(String src1, String src2) {

        File file = new File(src1);
        File file2 = new File(src2);

        FileInputStream fis = null;
        FileOutputStream fis2 = null;

        try {
            fis = new FileInputStream(file);
            fis2 = new FileOutputStream(file2);

            byte[] by = new byte[30];
            int length;

            try {
                while((length = fis.read(by))!=-1) {
                    fis2.write(by,0,length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis2!=null) {
                try {
                    fis2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
