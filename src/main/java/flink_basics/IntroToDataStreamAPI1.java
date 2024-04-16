package flink_basics;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import javax.xml.crypto.Data;

class Person{
    String name;
    int age;

    Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public String toString(){
        return this.name + ": age " + this.age;
    }
}
public class IntroToDataStreamAPI1 {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Person> flintsones = env.fromData(
                new Person("Fred", 35),
                new Person("Wilma", 30),
                new Person("Pebbles", 2)
                );

        DataStream<Person> adults = flintsones.filter(new FilterFunction<Person>() {
            @Override
            public boolean filter(Person value) throws Exception {
                return value.getAge() >= 18;
            }
        });

        adults.print();

        env.execute();
    }
}
