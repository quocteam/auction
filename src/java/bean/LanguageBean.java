/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author trung
 */

import java.io.Serializable;

import java.util.Locale;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="language")
@SessionScoped
public class LanguageBean implements Serializable{

	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void changeLanguage(String language) {
        locale = new Locale(language);
//        FacesContext.getCurrentInstance().getApplication().setDefaultLocale(new Locale(language));
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }

}
