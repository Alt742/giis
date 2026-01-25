package org.webvane.giis;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
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
public class GiisDMDKServiceTest extends FwMvcTest {

    @Ignore
    @Test
//    @Sql({ "/db/mysql/market/dicts.sql"})
    @FwConfig( value = {

    } ,
    deleteAll = true )
    public void test1() throws Exception {
        String json = "{\n" +
                "  \"requestType\": \"1\",\n" +
                "  \"algos\" : [ \"algo1\",\"algo2\"],\n" +
                "  \"templateParams\" : {\n" +
                "    \"param1\": \"text1\", \n" +
                "  \t\"param2\":  123324\n" +
                "  }\n" +
                "}";

        Map<String,Object> result = testPostParse( 200 ,  json,  FWCommands.COMMAND_METHOD ,  "IntGIIS","GiisDMDKAPI","getSendInfo" );
        assertNotNull( result );

        json = "{\n" +
                "  \"intServiceId\": 111," +
                "  \"sign\" : \"podpis\"," +
                "  \"sert\" : \"sert1\"" +
                "}";

        result = testPostParse( 200 ,  json,  FWCommands.COMMAND_METHOD ,  "IntGIIS","GiisDMDKAPI","send" );
        assertNotNull( result );
    }
}
