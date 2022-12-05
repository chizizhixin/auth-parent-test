package com.auth.system.custom;


import com.auth.system.utils.Md5Util;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//自定义密码组件
@Component
public class CustomMd5Password implements PasswordEncoder {
	public String encode(CharSequence rawPassword) {
		return Md5Util.md5(rawPassword.toString());
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(Md5Util.md5(rawPassword.toString()));
	}

}
