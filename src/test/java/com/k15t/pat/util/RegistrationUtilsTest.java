package com.k15t.pat.util;

import com.k15t.pat.ApplicationBootstrap;
import com.k15t.pat.constants.AppConstants;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationBootstrap.class})
public class RegistrationUtilsTest {

    @Test
    public void testDoBcrypt(){
        Assert.notNull(RegistrationUtils.doBCrypt(Mockito.anyString()));
    }
}
