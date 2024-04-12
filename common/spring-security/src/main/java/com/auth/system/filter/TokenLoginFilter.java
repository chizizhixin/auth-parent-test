package com.auth.system.filter;


import com.alibaba.fastjson.JSON;
import com.auth.model.vo.LoginVo;
import com.auth.system.custom.CustomUser;
import com.auth.system.result.Result;
import com.auth.system.result.ResultCodeEnum;
import com.auth.system.service.LoginLogService;
import com.auth.system.utils.IpUtil;
import com.auth.system.utils.JwtHelper;
import com.auth.system.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 * </p>
 *
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

	private RedisTemplate redisTemplate;

	private LoginLogService loginLogService;

	public TokenLoginFilter(AuthenticationManager authenticationManager,RedisTemplate redisTemplate,LoginLogService loginLogService) {
		this.setAuthenticationManager(authenticationManager);
		this.setPostOnly(false);
		//指定登录接口及提交方式，可以指定任意路径
		this.setRequiresAuthenticationRequestMatcher(new OrRequestMatcher(new AntPathRequestMatcher("/admin/system/index/login","POST"),new AntPathRequestMatcher("/applet/user/login","POST")));
		this.redisTemplate = redisTemplate;
		this.loginLogService = loginLogService;
	}

	/**
	 * 登录认证
	 * @param req
	 * @param res
	 * @return
	 * @throws AuthenticationException
	 */
	/**
	 * 登录认证
	 * @param req
	 * @param res
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			LoginVo loginVo = new ObjectMapper().readValue(req.getInputStream(), LoginVo.class);

			Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
			return this.getAuthenticationManager().authenticate(authenticationToken);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 登录成功
	 * @param request
	 * @param response
	 * @param chain
	 * @param auth
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication auth) throws IOException, ServletException {
		CustomUser customUser = (CustomUser) auth.getPrincipal();

		//保存权限数据
		redisTemplate.opsForValue().set(customUser.getUsername(), JSON.toJSONString(customUser.getAuthorities()));

		String token = JwtHelper.createToken(customUser.getSysUser().getId(), customUser.getSysUser().getUsername());

		//记录登录日志
		loginLogService.recordLoginLog(customUser.getUsername(),1, IpUtil.getIpAddress(request),"登录成功");

		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		ResponseUtil.out(response, Result.ok(map));
	}

	/**
	 * 登录失败
	 * @param request
	 * @param response
	 * @param e
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
											  AuthenticationException e) throws IOException, ServletException {

		if(e.getCause() instanceof RuntimeException) {
			ResponseUtil.out(response, Result.build(null, 204, e.getMessage()));
		} else {
			ResponseUtil.out(response, Result.build(null, ResultCodeEnum.LOGIN_MOBLE_ERROR));
		}
	}
}