package org.webvane.giis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.webvane.framework.annotation.FwConfig;
import org.webvane.framework.api.FMethodCallAPI;
import org.webvane.framework.service.FDataUtils;
import org.webvane.framework.unit.annotations.FwUnit;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.webvane.framework.test.FwTest.addUserToSess;

@RunWith(SpringJUnit4ClassRunner.class)
@FwUnit
public class IntProductTest {

    @Autowired
    private FMethodCallAPI methodCallAPI;


    @FwConfig( value = {
        "/configfw/int_product.json"
    }, deleteAll = true )
    @Test
    public void test1() throws Exception{
        addUserToSess( 1015L );

        Map<String, Object> mParams = FDataUtils.createMethodParamsFromString("param_name=PRODUCT_MATERIAL;dmdk_type=DM_SILVER;ump_type=Silver;processing_type=null");
        Map<String, Object> callResult = (Map<String, Object>)methodCallAPI.method( "saveIntdmdktransform" , null , mParams );
        Object id = callResult.get( "callResult");
        System.out.println("saveIntdmdktransform id : "+id );

        Map<String, Object> params;
        params = (Map<String, Object>)methodCallAPI.method("infoIntdmdktransform", id, null);
        System.out.println("params : " + params);

        mParams = FDataUtils.createMethodParamsFromString("ump_type=Gold");
        methodCallAPI.method("updateIntdmdktransform", id + "", mParams);

        params = (Map<String, Object>)methodCallAPI.method("infoIntdmdktransform", id, null);
        assertEquals( "Gold", params.get("ump_type") );

        Map<String, Object> listMap = (Map<String, Object>)methodCallAPI.method("listIntdmdktransform", null, null );
        List<Map<String, Object>> listparams = (List<Map<String, Object>>)listMap.get( "listdata");
        System.out.println("params : "+listparams );

        Map<String, Object> res = (Map<String, Object>)methodCallAPI.method( "removeIntdmdktransform" , id, null );
        System.out.println("removeIntdmdktransform : "+res );
    }

    @FwConfig( value = {
        "/configfw/int_product.json"
    }, deleteAll = true )
    @Test
    public void test2() throws Exception{
        addUserToSess( 1015L );

        Map<String, Object> mParams = FDataUtils.createMethodParamsFromString("id_goods=1;inserts_type=Insert1");
        Map<String, Object> callResult = (Map<String, Object>)methodCallAPI.method( "saveIntinserts" , null , mParams );
        Object id = callResult.get( "callResult");
        System.out.println("saveIntinserts id : "+id );

        Map<String, Object> params;
        params = (Map<String, Object>)methodCallAPI.method("infoIntinserts", id , null);
        System.out.println("params : " + params);

        mParams = FDataUtils.createMethodParamsFromString("inserts_type=Insert1");
        methodCallAPI.method("updateIntinserts", id, mParams);

        params = (Map<String, Object>)methodCallAPI.method("infoIntinserts", id, null);
        System.out.println("params : " + params);

        Map<String, Object> listMap = (Map<String, Object>)methodCallAPI.method("listIntinserts", null, null );
        List<Map<String, Object>> listparams = (List<Map<String, Object>>)listMap.get( "listdata");
        System.out.println("params : "+listparams );

        Map<String, Object> res = (Map<String, Object>)methodCallAPI.method( "removeIntinserts" , id, null );
        System.out.println("removeIntinserts : "+res );
    }

    @FwConfig( value = {
        "/configfw/int_product.json"
    }, deleteAll = true )
    @Test
    public void test3() throws Exception{
        addUserToSess( 1015L );

        Map<String, Object> mParams = FDataUtils.createMethodParamsFromString("name=name;material=material;description=description;hallmark=525;weight=323;weight_inserts=232;price=3233;size=323;brandId=323;article=article;additionally=additionally;producer=producer;uin_inp=uin_inp;okpd2=okpd2;tnved=tnved;owner=owner;update_time=update_time;");
        Map<String, Object> callResult = (Map<String, Object>)methodCallAPI.method( "saveIntproduct" , null , mParams );
        Object id = callResult.get( "callResult");
        System.out.println("saveIntproduct id : "+id );

        Map<String, Object> params;
        params = (Map<String, Object>)methodCallAPI.method("infoIntproduct", id, null);
        System.out.println("params : " + params);

        mParams = FDataUtils.createMethodParamsFromString("name=name2;");
        methodCallAPI.method("updateIntproduct", id, mParams);

        params = (Map<String, Object>)methodCallAPI.method("infoIntproduct", id, null);
        System.out.println("params : " + params);

        Map<String, Object> listMap = (Map<String, Object>)methodCallAPI.method("listIntproduct", null, null );
        List<Map<String, Object>> listparams = (List<Map<String, Object>>)listMap.get( "listdata");
        System.out.println("params : "+listparams );

        Map<String, Object> res = (Map<String, Object>)methodCallAPI.method( "removeIntproduct" , id , null );
        System.out.println("removeIntproduct : "+res );
    }

}
