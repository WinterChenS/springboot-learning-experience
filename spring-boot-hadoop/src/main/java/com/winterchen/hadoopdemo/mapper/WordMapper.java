package com.winterchen.hadoopdemo.mapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;

/*
 * 继承Mapper类需要定义四个输出、输出类型泛型：
 * 四个泛型类型分别代表：
 * KeyIn        Mapper的输入数据的Key，这里是每行文字的起始位置（0,11,...）
 * ValueIn      Mapper的输入数据的Value，这里是每行文字
 * KeyOut       Mapper的输出数据的Key，这里是每行文字中的单词"hello"
 * ValueOut     Mapper的输出数据的Value，这里是每行文字中的出现的次数
 *
 * Writable接口是一个实现了序列化协议的序列化对象。
 * 在Hadoop中定义一个结构化对象都要实现Writable接口，使得该结构化对象可以序列化为字节流，字节流也可以反序列化为结构化对象。
 * LongWritable类型:Hadoop.io对Long类型的封装类型
 */

public class WordMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);

    private Text word = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // 防止中文乱码
        String line = new String(value.getBytes(), 0, value.getLength(), "UTF-8").trim();
        if (StringUtils.isNotEmpty(line)) {
            // 使用分词器，分隔文件行内容根据常用的短语分隔，比如我们，被分隔成 <我,1>,<们,1><我们,1>
            byte[] btValue = line.getBytes();
            InputStream inputStream = new ByteArrayInputStream(btValue);
            Reader reader = new InputStreamReader(inputStream);
            IKSegmenter ikSegmenter = new IKSegmenter(reader, true);
            Lexeme lexeme;
            while ((lexeme = ikSegmenter.next()) != null) {
                word.set(lexeme.getLexemeText());
                context.write(word, one);
            }
        }
    }
}
