package com.yushixin.util.secret;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * 证书工具类
 */
public class CertUtil {

    /**
     * 解析证书cer文件
     * @param path cer文件路径
     */
    public static void parseCert(String path) throws IOException, CertificateException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));

        //是否解base64取决于你的证书，是base64存储还是二进制直接存放的
        // byte[] decode = Base64.decode(bytes);

        //将内容转成流的方式
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate certificate = (X509Certificate) cf.generateCertificate(bis);
        System.out.println( certificate.getIssuerDN().toString() );
        System.out.println( certificate.getSubjectDN().toString() );
        System.out.println( certificate );
    }

    private CertUtil() {}
}
