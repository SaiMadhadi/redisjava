package com.advjava;

import redis.clients.jedis.Jedis;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost");
        System.out.println(jedis.ping());

        // 1. Setting values
        jedis.set("sample1","helloworld1");
        jedis.set("sample2","helloworld1");

        //2. Getting values
        System.out.println(jedis.get("sample1"));
        System.out.println(jedis.get("sample2"));

        //3. Updating
        jedis.set("sample1","helloworld11");

        //4. Deleting
        jedis.del("sample2");

        //5. Setting Up expiry
        jedis.setex("sample3",10,"helloworld3");

        //Using List DS
        //1.Push
        jedis.lpush("customers","c1","c2","c3","c4");

        //2.Pop
        jedis.lpop("customers");

        // 3.Selecting range of data
        List<String> employeeList = jedis.lrange("customers",0,3);
        for(String employee : employeeList)
            System.out.println(employee);

        //Inserting multiple key and value pairs through one command
        String[] keyValuePairs = new String[]{"sample11","helloworld11","sample22","helloworld22"};
        jedis.mset(keyValuePairs);

        //Retrieving multiple key values in single call form Jedis
        String[] keys = new String[]{"sample1","sample2"};
        List<String> values = jedis.mget(keys);
        for(String value : values)
                System.out.println(value);

        //Counters
        System.out.println(jedis.get("int1"));
        // 1. Increment
        jedis.incr("int1");
        System.out.println(jedis.get("int1"));
        // 2. Decrement
        jedis.decr("int1");
        System.out.println(jedis.get("int1"));


    }
}
