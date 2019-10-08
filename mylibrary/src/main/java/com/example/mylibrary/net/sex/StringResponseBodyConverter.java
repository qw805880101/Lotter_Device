package com.example.mylibrary.net.sex;


import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;

/**
 * Created by psylife00 on 2017/5/26.
 */
public class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        try {
//            Map map = JSON.parseObject(value.string(), Map.class);
            String response = value.string();
//            if (map.get("code").toString().equals("0000")) {
//                System.out.println(response);
//            }
            return response;
        } finally {
            value.close();
        }
    }
}
