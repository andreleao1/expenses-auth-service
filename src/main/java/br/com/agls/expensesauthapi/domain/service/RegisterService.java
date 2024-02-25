package br.com.agls.expensesauthapi.domain.service;

import br.com.agls.expensesauthapi.domain.entity.user.User;

public interface RegisterService {

    void execute(User user);
}
