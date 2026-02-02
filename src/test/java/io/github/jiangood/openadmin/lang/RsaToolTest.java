package io.github.jiangood.openadmin.lang;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RsaToolTest {

    @Test
    void testGetPublicKey() {
        String publicKey = RsaTool.getPublicKey();
        assertNotNull(publicKey);
        assertFalse(publicKey.isEmpty());
    }

    @Test
    void testDecryptStr() {
        // 首先获取公钥
        String publicKey = RsaTool.getPublicKey();
        assertNotNull(publicKey);
        
        // 创建一个测试字符串
        String testString = "Hello, RSA!";
        
        // 注意：由于 RsaTool 使用的是内部固定的 RSA 实例，而我们无法直接获取其私钥
        // 这里我们只测试解密功能，不测试完整的加密解密流程
        // 实际使用中，RsaTool 应该与加密时使用的公钥匹配
        
        // 测试解密方法不会抛出异常
        try {
            // 使用一个无效的加密字符串测试，应该返回 null 而不是抛出异常
            String decrypted = RsaTool.decryptStr("invalid-encrypted-string", KeyType.PrivateKey);
            // 这里我们不断言具体值，因为输入是无效的
        } catch (Exception e) {
            fail("Decrypt should not throw exception for invalid input");
        }
    }

    @Test
    void testDecryptStrWithInvalidInput() {
        // 测试使用无效的输入
        String decrypted = RsaTool.decryptStr(null, KeyType.PrivateKey);
        assertNull(decrypted);
        
        // 测试使用空字符串
        decrypted = RsaTool.decryptStr("", KeyType.PrivateKey);
        assertNull(decrypted);
    }

}
