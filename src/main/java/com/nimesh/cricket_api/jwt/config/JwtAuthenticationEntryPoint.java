package com.nimesh.cricket_api.jwt.config;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		Map<String, String> message = new LinkedHashMap<String, String>();
		message.put("success", "false");

		// Check if the request as any exception that we have stored in Request
		final Exception exception = (Exception) request.getAttribute("exception");
		// If yes then use it to create the response message else use the authException
		if (exception != null) {
			message.put("cause", exception.getMessage());

			byte[] body = new ObjectMapper().writeValueAsBytes(message);
			response.getOutputStream().write(body);
		} else {
			message.put("error", authException.getMessage());

			byte[] body = new ObjectMapper().writeValueAsBytes(message);
			response.getOutputStream().write(body);
		}
	}

}
