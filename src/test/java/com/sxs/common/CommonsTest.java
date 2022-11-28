package com.sxs.common;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

//@SpringBootTest
public class CommonsTest {

    @Test
    public void md5() {
        String s = DigestUtils.md5Hex("123456");
        System.out.println(s);
        System.out.println("e10adc3949ba59abbe56e057f20f883e");
        System.out.println(s.equals("e10adc3949ba59abbe56e057f20f883e"));
//        System.out.println(StringUtils.isNotBlank("  "));
    }
}
