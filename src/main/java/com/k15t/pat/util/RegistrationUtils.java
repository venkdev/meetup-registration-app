package com.k15t.pat.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.StringWriter;

public class RegistrationUtils {

    public static String populateTemplateString(VelocityEngine velocityEngine, String templatePath, VelocityContext context){
        Template template = velocityEngine.getTemplate(templatePath);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

    public static String doBCrypt(String value) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(value);
    }
}
