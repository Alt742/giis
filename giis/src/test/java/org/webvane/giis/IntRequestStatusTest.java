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

import static org.webvane.framework.test.FwTest.addUserToSess;

@RunWith(SpringJUnit4ClassRunner.class)
@FwUnit
public class IntRequestStatusTest {

    @Autowired
    private FMethodCallAPI methodCallAPI;

    @FwConfig( value = {
        "/configfw/int_request_status.json"
    }, deleteAll = true )
    @Test
    public void test1() throws Exception{
        addUserToSess( 1015L );

        Map<String, Object> mParams = FDataUtils.createMethodParamsFromString("request_type=request_type;args=args;status=status");
        Map<String, Object> callResult = (Map<String, Object>)methodCallAPI.method( "saveIntrequeststatus" , null , mParams );
        Object id = callResult.get( "callResult");
        System.out.println("saveIntrequeststatus id : "+id );

        Map<String, Object> params;
        params = (Map<String, Object>)methodCallAPI.method("infoIntrequeststatus", id , null);
        System.out.println("params : " + params);

        mParams = FDataUtils.createMethodParamsFromString("status=status2");
        methodCallAPI.method("updateIntrequeststatus", id, mParams);

        params = (Map<String, Object>)methodCallAPI.method("infoIntrequeststatus", id , null);
        System.out.println("params : " + params);

        Map<String, Object> listMap = (Map<String, Object>)methodCallAPI.method("listIntrequeststatus", null, null );
        List<Map<String, Object>> listparams = (List<Map<String, Object>>)listMap.get( "listdata");
        System.out.println("params : "+listparams );

        Map<String, Object> res = (Map<String, Object>)methodCallAPI.method( "removeIntrequeststatus" , id , null );
        System.out.println("removeIntrequeststatus : "+res );
    }


}
