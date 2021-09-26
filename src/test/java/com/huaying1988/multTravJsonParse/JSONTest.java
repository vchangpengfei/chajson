package com.huaying1988.multTravJsonParse;

import static org.junit.jupiter.api.Assertions.*;

class JSONTest {

    @org.junit.jupiter.api.Test
    void parse() {
    }

    public static void main(String args[])
    {
        Object obj=JSON.parse("{\n\ta:[1,-23333,-0.3,0.17,5.2,\"\\u82B1\\u6979~\"],\n\tb:[\"a\tbc\",\"12  3\",\"4,5\\\"6\",{\n\t\t\t\t\tx:1,\n\t\t\t\t\ty:\"cc\\ncc\"\n\t\t\t\t},4.56],\n\t\"text\":\"I'm OK~\",\n\t\"1-2\":234,\n\tmybool:false,\n\tmynull:null,\n\tmyreal:true\n}\n");
        System.out.println(obj);

    }

}