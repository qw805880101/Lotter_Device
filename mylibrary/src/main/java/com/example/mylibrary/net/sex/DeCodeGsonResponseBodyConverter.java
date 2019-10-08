/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.mylibrary.net.sex;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;

final class DeCodeGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    DeCodeGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
//            Map<String, Object> map = JSON.parseObject(response, Map.class);

//            if (map.get("code").toString().equals("0000")) {
//                response = Utils.deCode(map.get("result").toString());
//                Map<String, Object> dataMap = JSON.parseObject(response, Map.class);
//                map.remove("result");
//                map.put("result", dataMap);
//                response = JSON.toJSONString(map);
//            }
//            BaseBeanInfo mBaseBeanInfo = new BaseBeanInfo();
//            try {
//                response = new String(DES3code.decrypt(Base64Class.decode(response, 0), WRCoreApp.desKey.getBytes()));
//                LogUtil.d("response", response);
//            } catch (Exception e) {
//                e.printStackTrace();
//                mBaseBeanInfo.setRespCode("9999");
//                mBaseBeanInfo.setRespDesc(response);
//                response = JSON.toJSONString(mBaseBeanInfo);
//            }

            value = ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), response);
            JsonReader jsonReader = gson.newJsonReader(value.charStream());
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
