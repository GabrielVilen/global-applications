package se.kth.iv1201projekt.businesslogic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 * A bean that handles the changing of languages in the website
 *
 * @author Samy
 */
@Named(value = "languageBean")
@SessionScoped
public class LanguageBean implements Serializable {

    private String localeTag = "en";
    private static Map<String, Object> languages;

    /**
     * Initializes once. Gets all the supported locales from FacesConfig
     *
     */
    static {
        languages = new HashMap<>();
        Application facesApp = FacesContext.getCurrentInstance().getApplication();
        Locale defLoc = facesApp.getDefaultLocale();
        languages.put(defLoc.getDisplayLanguage(), defLoc);

        Iterator<Locale> locales = facesApp.getSupportedLocales();
        while (locales.hasNext()) {
            Locale loc = locales.next();
            languages.put(loc.getDisplayLanguage(), loc);
        }
    }

    public String getLocale() {
        return localeTag;
    }

    public void setLocale(String locale) {
        this.localeTag = locale;
    }

    public Map<String, Object> getLanguages() {
        return languages;
    }

    /**
     * Changing the locale in the facecontext instance to the one chosen by the
     * user.
     *
     * @param ev
     */
    public void localeChanged(ValueChangeEvent ev) {
        String newLocaleValue = ev.getNewValue().toString();
        for (Map.Entry<String, Object> entry : languages.entrySet()) {
            if (entry.getValue().toString().equals(newLocaleValue)) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
    }

}
