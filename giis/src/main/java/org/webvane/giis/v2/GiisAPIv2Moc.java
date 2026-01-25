package org.webvane.giis.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.google.gson.Gson;
import org.webvane.framework.api.FMethodCallAPI;
import org.webvane.framework.fw;
import org.webvane.framework.mvc.ResultFW;
import org.webvane.giis.dmdk.request.dmdk.Envelope;
import org.webvane.giis.dmdk.request.dmdk.elements.response.batch.ResultBatch;
import org.webvane.giis.dmdk.request.dmdk.elements.response.contractor.ResultContractor;
import org.webvane.giis.model.*;
import org.webvane.giis.v2.model.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GiisAPIv2Moc implements GiisAPIv2 {

    @Override
    public ResultFW registerRequest(String requestType, Map<String, Object> args) {
        ResultFW res = new ResultFW();
        res.success = true;
        res.params = new LinkedHashMap<>();
        res.params.put("requestId", 1101);
        return res;
    }

    @Override
    public CheckResult checkRequest(Long id) {
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
//        mapper.setAnnotationIntrospector(introspector);

        CheckResult result = new CheckResult();
        result.status = "PREPARED";
        Map<String, Object> out = new HashMap<>();
        out.put("response", readStringAsMap(  "{\"messageId\":\"5de4600d-73f5-4484-a05d-7eb255e66384\",\"status\":\"PREPARED\",\"page\":1,\"pages\":1,\"size\":1,\"result\":[{\"OKPD2\":\"32.12.13.110\",\"UIN_INP\":\"6432205195556928\",\"batchProduct\":{\"article\":\"774033\",\"brand\":\"Электрум\",\"confirmHallmark\":\"58500\",\"dateManufacture\":\"2024-09-17Z\",\"hallmark\":\"58500\",\"metal\":\"DM_GOLD\",\"metalList\":[{\"hallmark\":58500,\"metal\":\"DM_GOLD\",\"weight\":58000}],\"stoneList\":[{\"classCode\":\"910310060300501\",\"color\":\"DIAMOND_3\",\"quality\":\"DIAMOND_5_GROUP\",\"quantity\":6,\"shape\":\"DIAMOND_KR_57\",\"type\":\"DIAMOND\",\"uom\":\"CTM\",\"weight\":2200}]},\"category\":\"JT_SUSPENSION\",\"description\":\"774033 (Подвеска) (Au 585 К) (03-крест-м), вст. 6 брл Кр-57 0,110Ct 3/5 7700000054852 1,01\",\"keeper\":{\"physical\":{\"IDTOP\":\"ИП770100160400000\",\"OGRN\":\"311774605600621\",\"INN\":\"772970872862\"},\"info\":{\"name\":\"Индивидуальный предприниматель Козлов Олег Игоревич\"}},\"name\":\"774033 (Подвеска) (Au 585 К) (03-крест-м)\",\"owner\":{},\"phase\":\"MANUFACTURING_PRODUCT\",\"process\":\"STORED\",\"producer\":{\"info\":{\"name\":\"Индивидуальный предприниматель Козлов Олег Игоревич\"},\"physical\":{\"IDTOP\":\"ИП770100160400000\",\"OGRN\":\"311774605600621\",\"INN\":\"772970872862\"}},\"quantity\":1,\"remains\":false,\"status\":\"STORING\",\"subType\":\"JEWERLY\",\"type\":\"PRODUCT\",\"uom\":\"GRM\",\"weight\":101000}]}" ));
        result.data = out;
        return result;
    }

    @Override
    public OrgInfo checkOrg(Long inn) {
        OrgInfo result = new OrgInfo();

        result.status = "OK";
        result.data = readUsingXMLMapper( "{\n" +
                "        \"INN\": \"772970872862\",\n" +
                "        \"OGRN\": \"311774605600621\",\n" +
                "        \"IDTOP\": \"ИП770100160400000\",\n" +
                "        \"address\": \"125362 город Москва улица Свободы дом 35 строение 18 Этаж 5, помещение VII, комнаты 11-14\",\n" +
                "        \"grantSale\": true\n" +
                "    }", ResultContractor.class);
        result.updateTime = new Long("1733325368177");
        return result;
    }

    @Override
    public ResultFW setPropucts ( List<String> uins ){
        ResultFW res = new ResultFW();
        res.success = true;
        res.params = new LinkedHashMap<>();
        res.params.put("requestId", 1104);

        for( String uin : uins ){
            this.uins.put( uin, new ShortResultBatch(  "774032 (Подвеска) (Au 585 К) (03-крест-м)", uin, "UNKNOWN" ) );
        }
        return res;
    }

    @Override
    public ResultFW delProduct ( Long id ){
        ResultFW res = new ResultFW();
        res.success = true;

        return res;
    }

    Map<String,ShortResultBatch> uins = new ConcurrentHashMap<>();

    @Override
    public CheckUinsResult getPropuctUinStatus (Long id){
        CheckUinsResult res = new CheckUinsResult();
        res.status = "PREPARED";
        res.uins = new LinkedList<>();
        for( String uin : this.uins.keySet() ){
            res.uins.add( this.uins.get( uin ) );
        }
//        res.uins.add(new ShortResultBatch( "774033 (Подвеска) (Au 585 К) (03-крест-м)",  "OK"));
//        res.uins.add(new ShortResultBatch("774032 (Подвеска) (Au 585 К) (03-крест-м)", "6432205789006138", "FAIL"));
//        res.uins.add(new ShortResultBatch("0774050 (Кольцо) (Au 585 Б) (011148)", "6432400638905261", "FAIL"));
//        res.uins.add(new ShortResultBatch("0774011 (Кольцо) (Au 585 Б) (011148)", "6432400638905255", "FAIL"));

        return res;
    }

    @Override
    public CheckPropuctStatus getPropuctStatus (Long id){
        CheckPropuctStatus res = new CheckPropuctStatus();
        res.status = "PREPARED";
        return res;
    }

    @Override
    public ResultFW finishPropuct (Long id){
        ResultFW res = new ResultFW();
        res.success = true;
        res.params = new LinkedHashMap<>();
        res.params.put("requestId", 1104);
        return res;
    }

    @Override
    public ResultFW deletePropuctUin (Long id, String uin ){

        uins.remove( uin );

        ResultFW res = new ResultFW();
        res.success = true;
        res.params = new LinkedHashMap<>();
        res.params.put("requestId", 1104);
        return res;
    }

    @Override
    public ResultFW addPropuctUin (Long id, String uin ){

        uins.put( uin, new ShortResultBatch(  "774033 (Колье) (Au  К) (03-крест-м)", uin,"OK") );

        ResultFW res = new ResultFW();
        res.success = true;
        res.params = new LinkedHashMap<>();
        res.params.put("requestId", 1104);
        return res;
    }

    @Override
    public CheckPropuctResult getPropuctMergedInfo (Long id ){
        CheckPropuctResult cpr = new CheckPropuctResult();
        cpr.status = "PREPARED";
        cpr.uins = new LinkedList<>();

        for( String uin : this.uins.keySet() ){
            cpr.uins.add( this.uins.get( uin ) );
        }

//        cpr.uins.add(new ShortResultBatch("774033 (Подвеска) (Au 585 К) (03-крест-м)", "6432205195556928", "OK"));
//        cpr.uins.add(new ShortResultBatch("774032 (Подвеска) (Au 585 К) (03-крест-м)", "6432205789006138", "FAIL"));
//        cpr.uins.add(new ShortResultBatch("0774050 (Кольцо) (Au 585 Б) (011148)", "6432400638905261", "FAIL"));
//        cpr.uins.add(new ShortResultBatch("0774011 (Кольцо) (Au 585 Б) (011148)", "6432400638905255", "FAIL"));

        String json = "{\n" +
                "    \"status\": \"FINISHED\",\n" +
                "    \"uins\": [\n" +
                "        {\n" +
                "            \"valid\": \"OK\",\n" +
                "            \"name\": \"772992 (Кольцо) (Au 585 К) (01630)\",\n" +
                "            \"uin\": \"6432400272682474\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"valid\": \"UNKNOWN\",\n" +
                "            \"name\": \"\",\n" +
                "            \"uin\": \"6432300750668917\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"valid\": \"FAIL\",\n" +
                "            \"name\": \"772490 (Подвеска) (Au 585 Ж) (03445)\",\n" +
                "            \"uin\": \"6432202500557923\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"valid\": \"FAIL\",\n" +
                "            \"name\": \"772930 (Серьги) (Au 585 Б) (021220)\",\n" +
                "            \"uin\": \"6432204550594058\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"valid\": \"FAIL\",\n" +
                "            \"name\": \"772928 (Кольцо) (Au 585 К) (01703)\",\n" +
                "            \"uin\": \"6432400272682343\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"data\": {\n" +
                "        \"index\": null,\n" +
                "        \"uin_inp\": \"6432400272682474\",\n" +
                "        \"name\": \"772992 (Кольцо) (Au 585 К) (01630)\",\n" +
                "        \"keeper\": {\n" +
                "            \"physical\": {\n" +
                "                \"inn\": \"773415164340\",\n" +
                "                \"idtop\": \"ИП770100374900000\",\n" +
                "                \"ogrn\": \"314774627200640\"\n" +
                "            },\n" +
                "            \"info\": {\n" +
                "                \"name\": \"Индивидуальный предприниматель Пономарев Игорь Евгеньевич\",\n" +
                "                \"email\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"address\": null\n" +
                "            },\n" +
                "            \"inn\": null,\n" +
                "            \"short_name\": null,\n" +
                "            \"idtop\": null\n" +
                "        },\n" +
                "        \"remains\": false,\n" +
                "        \"description\": \"772992 (Кольцо) (Au 585 К) (01630), разм. 18.0, вст. 1 брл Кр-57 0,250Ct 3/6 7700000052193 4,11\",\n" +
                "        \"type\": \"PRODUCT\",\n" +
                "        \"subType\": \"JEWERLY\",\n" +
                "        \"category\": \"JT_RING\",\n" +
                "        \"phase\": \"MANUFACTURING_PRODUCT\",\n" +
                "        \"process\": \"STORED\",\n" +
                "        \"status\": \"STORING\",\n" +
                "        \"okpd2\": \"32.12.13.110\",\n" +
                "        \"tnved\": null,\n" +
                "        \"quantity\": 1,\n" +
                "        \"weight\": 411000,\n" +
                "        \"producer\": {\n" +
                "            \"physical\": {\n" +
                "                \"inn\": \"773415164340\",\n" +
                "                \"idtop\": \"ИП770100374900000\",\n" +
                "                \"ogrn\": \"314774627200640\"\n" +
                "            },\n" +
                "            \"info\": {\n" +
                "                \"name\": \"Индивидуальный предприниматель Пономарев Игорь Евгеньевич\",\n" +
                "                \"email\": null,\n" +
                "                \"phone\": null,\n" +
                "                \"address\": null\n" +
                "            }\n" +
                "        },\n" +
                "        \"owner\": {},\n" +
                "        \"batchProduct\": {\n" +
                "            \"metal\": \"DM_GOLD\",\n" +
                "            \"hallmark\": null,\n" +
                "            \"confirmHallmark\": null,\n" +
                "            \"mixMarkType\": null,\n" +
                "            \"metalList\": [\n" +
                "                {\n" +
                "                    \"sortNumber\": 0,\n" +
                "                    \"hallmark\": 58500,\n" +
                "                    \"confirmHallmark\": null,\n" +
                "                    \"metal\": \"DM_GOLD\",\n" +
                "                    \"mixMarkType\": null,\n" +
                "                    \"weight\": 238000,\n" +
                "                    \"contentGrammOnTon\": null\n" +
                "                }\n" +
                "            ],\n" +
                "            \"stoneList\": [\n" +
                "                {\n" +
                "                    \"type\": \"DIAMOND\",\n" +
                "                    \"classCode\": \"910310005300602\",\n" +
                "                    \"shape\": \"DIAMOND_KR_57\",\n" +
                "                    \"quality\": \"DIAMOND_6_GROUP\",\n" +
                "                    \"color\": \"DIAMOND_3\",\n" +
                "                    \"quantity\": 1,\n" +
                "                    \"weight\": 5000,\n" +
                "                    \"uom\": \"CTM\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"nuggetList\": null,\n" +
                "            \"otherCompositionList\": null,\n" +
                "            \"dateManufacture\": \"2024-06-12Z\",\n" +
                "            \"brand\": \"Электрум\",\n" +
                "            \"article\": \"772992\",\n" +
                "            \"imageList\": null,\n" +
                "            \"jewelryType\": null,\n" +
                "            \"size\": \"JT_RING_18\",\n" +
                "            \"serialNumber\": null,\n" +
                "            \"inp\": null\n" +
                "        },\n" +
                "        \"ownerList\": null,\n" +
                "        \"costList\": null,\n" +
                "        \"parentList\": null,\n" +
                "        \"uinList\": null,\n" +
                "        \"storageList\": null,\n" +
                "        \"property\": {},\n" +
                "        \"repair\": null,\n" +
                "        \"fpp\": null,\n" +
                "        \"set\": null,\n" +
                "        \"cheque\": null,\n" +
                "        \"inn\": null\n" +
                "    },\n" +
                "    \"variants\": [\n" +
                "        {\n" +
                "            \"first\": [\n" +
                "                {\n" +
                "                    \"valid\": \"OK\",\n" +
                "                    \"name\": \"772992 (Кольцо) (Au 585 К) (01630)\",\n" +
                "                    \"uin\": \"6432400272682474\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"second\": {\n" +
                "                \"index\": null,\n" +
                "                \"uin_inp\": \"6432400272682343\",\n" +
                "                \"name\": \"772928 (Кольцо) (Au 585 К) (01703)\",\n" +
                "                \"keeper\": {\n" +
                "                    \"physical\": {\n" +
                "                        \"inn\": \"773415164340\",\n" +
                "                        \"idtop\": \"ИП770100374900000\",\n" +
                "                        \"ogrn\": \"314774627200640\"\n" +
                "                    },\n" +
                "                    \"info\": {\n" +
                "                        \"name\": \"Индивидуальный предприниматель Пономарев Игорь Евгеньевич\",\n" +
                "                        \"email\": null,\n" +
                "                        \"phone\": null,\n" +
                "                        \"address\": null\n" +
                "                    },\n" +
                "                    \"inn\": null,\n" +
                "                    \"short_name\": null,\n" +
                "                    \"idtop\": null\n" +
                "                },\n" +
                "                \"remains\": false,\n" +
                "                \"description\": \"772928 (Кольцо) (Au 585 К) (01703), разм. 16.0, вст. 15 брл Кр-57 0,070Ct 3/6 7700000052216 2,04\",\n" +
                "                \"type\": \"PRODUCT\",\n" +
                "                \"subType\": \"JEWERLY\",\n" +
                "                \"category\": \"JT_RING\",\n" +
                "                \"phase\": \"MANUFACTURING_PRODUCT\",\n" +
                "                \"process\": \"STORED\",\n" +
                "                \"status\": \"STORING\",\n" +
                "                \"okpd2\": \"32.12.13.110\",\n" +
                "                \"tnved\": null,\n" +
                "                \"quantity\": 1,\n" +
                "                \"weight\": 204000,\n" +
                "                \"producer\": {\n" +
                "                    \"physical\": {\n" +
                "                        \"inn\": \"773415164340\",\n" +
                "                        \"idtop\": \"ИП770100374900000\",\n" +
                "                        \"ogrn\": \"314774627200640\"\n" +
                "                    },\n" +
                "                    \"info\": {\n" +
                "                        \"name\": \"Индивидуальный предприниматель Пономарев Игорь Евгеньевич\",\n" +
                "                        \"email\": null,\n" +
                "                        \"phone\": null,\n" +
                "                        \"address\": null\n" +
                "                    }\n" +
                "                },\n" +
                "                \"owner\": {},\n" +
                "                \"batchProduct\": {\n" +
                "                    \"metal\": \"DM_GOLD\",\n" +
                "                    \"hallmark\": null,\n" +
                "                    \"confirmHallmark\": null,\n" +
                "                    \"mixMarkType\": null,\n" +
                "                    \"metalList\": [\n" +
                "                        {\n" +
                "                            \"sortNumber\": 0,\n" +
                "                            \"hallmark\": 58500,\n" +
                "                            \"confirmHallmark\": null,\n" +
                "                            \"metal\": \"DM_GOLD\",\n" +
                "                            \"mixMarkType\": null,\n" +
                "                            \"weight\": 119000,\n" +
                "                            \"contentGrammOnTon\": null\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"stoneList\": [\n" +
                "                        {\n" +
                "                            \"type\": \"DIAMOND\",\n" +
                "                            \"classCode\": \"910311400300601\",\n" +
                "                            \"shape\": \"DIAMOND_KR_57\",\n" +
                "                            \"quality\": \"DIAMOND_6_GROUP\",\n" +
                "                            \"color\": \"DIAMOND_3\",\n" +
                "                            \"quantity\": 15,\n" +
                "                            \"weight\": 1400,\n" +
                "                            \"uom\": \"CTM\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"nuggetList\": null,\n" +
                "                    \"otherCompositionList\": null,\n" +
                "                    \"dateManufacture\": \"2024-06-12Z\",\n" +
                "                    \"brand\": \"Электрум\",\n" +
                "                    \"article\": \"772928\",\n" +
                "                    \"imageList\": null,\n" +
                "                    \"jewelryType\": null,\n" +
                "                    \"size\": null,\n" +
                "                    \"serialNumber\": null,\n" +
                "                    \"inp\": null\n" +
                "                },\n" +
                "                \"ownerList\": null,\n" +
                "                \"costList\": null,\n" +
                "                \"parentList\": null,\n" +
                "                \"uinList\": null,\n" +
                "                \"storageList\": null,\n" +
                "                \"property\": {},\n" +
                "                \"repair\": null,\n" +
                "                \"fpp\": null,\n" +
                "                \"set\": null,\n" +
                "                \"cheque\": null,\n" +
                "                \"inn\": null\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"first\": [\n" +
                "                {\n" +
                "                    \"valid\": \"FAIL\",\n" +
                "                    \"name\": \"772490 (Подвеска) (Au 585 Ж) (03445)\",\n" +
                "                    \"uin\": \"6432202500557923\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"valid\": \"FAIL\",\n" +
                "                    \"name\": \"772930 (Серьги) (Au 585 Б) (021220)\",\n" +
                "                    \"uin\": \"6432204550594058\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"valid\": \"FAIL\",\n" +
                "                    \"name\": \"772928 (Кольцо) (Au 585 К) (01703)\",\n" +
                "                    \"uin\": \"6432400272682343\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"second\": null\n" +
                "        }\n" +
                "    ]\n" +
                "}";
//        ObjectMapper mapper = new ObjectMapper();
        try {
//            Gson gson = new Gson(); // Or use new GsonBuilder().create();
//            cpr.data = gson.fromJson(json, ResultBatch.class);
            cpr.data = readUsingXMLMapper( json, ResultBatch.class );
            return cpr;
        }
        catch (Exception e){

        }
        return cpr;
    }

    public static Map readStringAsMap( Object str ) {
        if (str == null) {
            return null;
        }
        try {
            Map<String, Object> obj = mapper.readValue(str.toString(), Map.class);
            return obj;
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T readUsingXMLMapper( Object str, Class<T> clazz ) {
        if (str == null) {
            return null;
        }
        try {
            return xmlMapper.readValue( str.toString(), clazz );
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    static ObjectMapper xmlMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    static{
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.registerModule(new JaxbAnnotationModule());

//        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
//        xmlMapper.setAnnotationIntrospector(introspector);
    }
}
