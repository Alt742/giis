package org.webvane.giis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
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
public class IntActionTest {

    @Autowired
    private FMethodCallAPI methodCallAPI;

    @Test
//    @Sql({ "/db/mysql/market/dicts.sql"})
    @FwConfig( value = {
            "/configfw/int_action.json"
    }, deleteAll = true )
    public void test1() throws Exception {
        addUserToSess( 1015L );

        Map<String, Object> mParams = FDataUtils.createMethodParamsFromString(
                "system_id=1;process_id=1;message_id=232323;type=GIIS;status=NEW;request=request;response=response");
        Map<String, Object> callResult = (Map<String, Object>)methodCallAPI.method( "saveIntaction" , null , mParams );
        Object id = callResult.get( "callResult");
        System.out.println("saveIntaction id : "+id );

        Map<String, Object> params;
        params = (Map<String, Object>)methodCallAPI.method("infoIntaction", id + "", null);
        System.out.println("params : " + params);

        mParams = FDataUtils.createMethodParamsFromString("status=SENT");
        methodCallAPI.method("updateIntaction", id + "", mParams);

        params = (Map<String, Object>)methodCallAPI.method("infoIntaction", id + "", null);
        System.out.println("params : " + params);

        Map<String, Object> listMap = (Map<String, Object>)methodCallAPI.method("listIntaction", null, null );
        List<Map<String, Object>> listparams = (List<Map<String, Object>>)listMap.get( "listdata");
        System.out.println("params : "+listparams );

        Map<String, Object> res = (Map<String, Object>)methodCallAPI.method( "removeIntaction" , id+"" , null );
        System.out.println("removeIntaction : "+res );
    }
}
