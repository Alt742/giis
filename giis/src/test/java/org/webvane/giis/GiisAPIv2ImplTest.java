package org.webvane.giis;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.webvane.framework.annotation.FwConfig;
import org.webvane.framework.api.FWCommands;
import org.webvane.framework.test.FwMvcTest;
import org.webvane.framework.unit.annotations.FwUnitTest;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath:springcontext/common.xml",
        "classpath:springcontext/dictionary.xml",
        "classpath:springcontext/annotationsCache.xml",
        "classpath:springcontext/frameworkbase.xml",
        "classpath:springcontext/framework.xml",
        "classpath:springcontext/frameworkhttp.xml",
        "classpath:springcontext/startertest.xml",
        "classpath:springcontext/frameworkunit.xml",
        "classpath:springcontext/frameworkunit.acl.xml",
        "classpath:springcontext/fw.bean.queue.adaptor.xml",

        "classpath:springcontext/giisdmdk.xml"
})
@FwUnitTest
public class GiisAPIv2ImplTest extends FwMvcTest {


    @Test
//    @Sql({ "/db/mysql/market/dicts.sql"})
    @FwConfig( value = {
        "/configfw/giis_api.v2.json"
    } ,
    deleteAll = true )
    public void test1() throws Exception {
        String json = "{\n" +
                "    \"requestType\": \"SendGetBatchBriefRequest\",\n" +
                "    \"args\": {\n" +
                "        \"uin\": [\"6432202500557923\", \"6432400272682343\", \"6432300750668917\"]\n" +
                "    }\n" +
                "}";

        Map<String,Object> result = testPostParse( 200,  json,  FWCommands.COMMAND_METHOD ,  "IntGIIS","GiisAPI","registerRequest" );
        assertNotNull( result );

        json = "{\"id\": 1}";

        // call "IntGIIS/GiisAPI/checkRequest/pk/1"
        result = testGetParse( 200, null, FWCommands.COMMAND_METHOD ,  "IntGIIS","GiisAPI","checkRequest","pk", "1" );
        assertNotNull( result );
    }
}
