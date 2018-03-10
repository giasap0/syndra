package com.sapo.drakkar.common.autentication;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by giampaolo.saporito
 * on 29.12.2017 - venerdì
 */
@NameBinding
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface AuthenticationNotRequired
{
}
