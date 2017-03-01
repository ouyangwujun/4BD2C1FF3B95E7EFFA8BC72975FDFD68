package com.object.test.api.open;

import com.object.test.api.open.entity.request.TestRequest;
import com.object.test.api.open.entity.response.TestResponse;

/**
 * Created by PUZE81 on 2017/2/27.
 */
public interface IDubboTest {

    void testMathed();

    TestResponse testMathed(TestRequest testRequest);
}
