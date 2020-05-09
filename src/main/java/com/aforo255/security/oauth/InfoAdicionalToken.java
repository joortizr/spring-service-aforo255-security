package com.aforo255.security.oauth;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.aforo255.security.model.dao.UsuarioDao;
import com.aforo255.security.model.entity.Usuario;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	private Logger log = LoggerFactory.getLogger(InfoAdicionalToken.class);

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		
		Usuario usuario = usuarioDao.findByUsername(authentication.getName());
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Error login");
		}
		
		log.info("Usuario autenticado correo: " + usuario.getEmail());
		
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("email", usuario.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
