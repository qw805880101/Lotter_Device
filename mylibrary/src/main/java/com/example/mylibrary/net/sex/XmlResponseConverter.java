package com.example.mylibrary.net.sex;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by tc on 2017/6/13.
 */

public class XmlResponseConverter<T> implements Converter<ResponseBody, T> {

    private Type type;

    private String beanRoot;

    public XmlResponseConverter(Type type, String beanRoot) {
        this.type = type;
        this.beanRoot = beanRoot;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        try {
            String str = responseBody.string();
            T obj = null;
            try {
                obj = (T) XmlUtils.getBeanListByParseXml(str, beanRoot, getClass());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        } finally {
            responseBody.close();
        }
    }

}
