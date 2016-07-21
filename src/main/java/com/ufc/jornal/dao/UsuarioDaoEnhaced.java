package com.ufc.jornal.dao;

import com.ufc.jornal.model.Usuario;

public interface UsuarioDaoEnhaced {
	public Usuario findByLoginLike(String login);
}
