package com.winterchen.hadoopdemo.reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * 继承Reducer类需要定义四个输出、输出类型泛型：
 * 四个泛型类型分别代表：
 * KeyIn        Reducer的输入数据的Key，这里是每行文字中的单词"hello"
 * ValueIn      Reducer的输入数据的Value，这里是每行文字中的次数
 * KeyOut       Reducer的输出数据的Key，这里是每行文字中的单词"hello"
 * ValueOut     Reducer的输出数据的Value，这里是每行文字中的出现的总次数
 */
public class WordReduce extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable result = new IntWritable();
    private List<String> textList = new ArrayList<>();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result);

        String keyStr = key.toString();
        // 未使用分词器，需要根据map传过来的行内容检索并累加
        // boolean isHas = keyStr.contains(text);
        // if (isHas) {
        // textSum++;
        // System.out.println("============ " + text + " 统计分词为: " + textSum + "
        // ============");
        // }

        // 使用分词器，内容已经被统计好了，直接输出即可
        if (textList.contains(keyStr)) {
            System.out.println("============ " + keyStr + " 统计分词为: " + sum + " ============");
        }
    }
}
