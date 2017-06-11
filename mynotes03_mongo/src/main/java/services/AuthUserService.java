package services;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthUserService implements UserDetailsService {
	private final String[] users = {"u001","u002","u003"};
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		boolean userFound = false;
		for(String userns : users){
			if (userns.equals(username)){
				userFound = true;
				break;
			}
		}

		if (userFound){
			GrantedAuthority[] authoritiesArr = new GrantedAuthority[1];
			Arrays.fill(authoritiesArr, new SimpleGrantedAuthority("ROLE_USER"));
			List<GrantedAuthority> authorities = Arrays.asList(authoritiesArr);
			return new User(username,"aaa",authorities);
		}else{
			throw new UsernameNotFoundException(username);
		}
	}

}
