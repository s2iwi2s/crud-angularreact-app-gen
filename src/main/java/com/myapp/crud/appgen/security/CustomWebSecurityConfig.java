package com.myapp.crud.appgen.security;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.myapp.crud.appgen.security.jwt.JwtTokenAuthorizationOncePerRequestFilter;
import com.myapp.crud.appgen.security.jwt.JwtUnAuthorizedResponseAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AuthorizedUrlRepository authorizedUrlRepository;

	@Autowired
	private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter;

	@Value("${jwt.get.token.uri}")
	private String authenticationPath;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().exceptionHandling()
				.authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint)
				// and sessionManagement
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				// authorizeRequests
				.and().authorizeRequests().antMatchers("/", "/static/**").permitAll().anyRequest().authenticated();

		httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

//		httpSecurity.headers().frameOptions().sameOrigin() // H2 Console Needs this setting
//				.cacheControl(); // disable caching
	}

	@Override
	public void configure(final WebSecurity webSecurity) throws Exception {
		Iterable<AuthorizedUrl> authorizedUrls = authorizedUrlRepository.findAll();

		log.info("");
		log.info("");
		log.info("#############################################");
		log.info("Urls:" + StreamSupport.stream(authorizedUrls.spliterator(), false).map(AuthorizedUrl::getUrl)
				.collect(Collectors.toList()));

		List<String> defaultPermitAllUrls = StreamSupport.stream(authorizedUrls.spliterator(), false)
				.filter(AuthorizedUrl::isPermit).map(AuthorizedUrl::getUrl).collect(Collectors.toList());

		log.info("Ignoring antMatchers:" + defaultPermitAllUrls);

		log.info("#############################################");
		log.info("");
		log.info("");

		webSecurity.ignoring().antMatchers(HttpMethod.POST, authenticationPath).antMatchers(HttpMethod.OPTIONS, "/**")
				.and().ignoring().antMatchers(HttpMethod.GET, "/")
				// Other Stuff You want to Ignore
				.and().ignoring().antMatchers(defaultPermitAllUrls.toArray(new String[defaultPermitAllUrls.size()]));
//				.and().ignoring().antMatchers("/", "*", "/login", "/logout", "/favicon.ico", "/css/**", "/static/**",
//						"/js/**", "/*.js", "/*.css", "styles.*.css", "/api/home/**",
//						"", "/actuator/**");

//            .ignoring()
//            .antMatchers("/h2-console/**/**");//Should not be in Production!
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
