package io.github.jiangood.openadmin.lang;

import cn.hutool.core.text.PasswdStrength;
import cn.hutool.core.util.RandomUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import static cn.hutool.core.util.RandomUtil.BASE_CHAR_NUMBER;

public class PasswordTool {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder() ;

    public static String random() {
        String specialChars = "_-!.@$^&*()+=";
        String digits = "0123456789";
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder password = new StringBuilder();

        // 确保至少包含一个数字
        password.append(RandomUtil.randomChar(digits));

        // 确保至少包含一个特殊字符
        password.append(RandomUtil.randomChar(specialChars));

        // 填充剩余字符
        String allChars = BASE_CHAR_NUMBER + specialChars;
        for (int i = 2; i < 12; i++) {
            password.append(RandomUtil.randomChar(allChars));
        }

        // 打乱字符顺序
        char[] chars = password.toString().toCharArray();
        cn.hutool.core.util.ArrayUtil.shuffle(chars);
        return new String(chars);
    }

    /**
     * 生产密码的密文，每次调用都不一样
     *
     * @param plainText
     */
    public static String encode(String plainText) {
        return getEncoder().encode(plainText);
    }

    public static PasswordEncoder getEncoder() {
        return PASSWORD_ENCODER;
    }


    /**
     * 校验密码强度
     *
     * @param password
     */
    public static void validateStrength(String password) {
        Assert.state(isStrengthOk(password), "密码强度太低");

    }

    public static boolean isStrengthOk(String password) {
        return PasswdStrength.getLevel(password).ordinal() > PasswdStrength.PASSWD_LEVEL.EASY.ordinal();
    }

}
