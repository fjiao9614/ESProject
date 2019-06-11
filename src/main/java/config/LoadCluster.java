package config;

import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoadCluster {


    public static String load_value(String fileName,String key){
        String value = "";
        @Cleanup
        BufferedReader bufferedReader;

        Properties properties = new Properties();
        // 使用InPutStream流读取properties文件
        try {
        bufferedReader = new BufferedReader(new FileReader("src/main/resources/"+fileName));
        // 使用properties对象加载输入流
            properties.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取key对应的value值
        properties.getProperty(key);
        System.out.println(properties.getProperty(key));

        return value;
    }
    public static void main(String[] args) {
        load_value("application.properties","kafka_cluster");
    }
}
