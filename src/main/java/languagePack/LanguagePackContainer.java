package languagePack;

public class LanguagePackContainer {


    private static LanguagePackContainer singleInstance = new LanguagePackContainer();
    private LanguagePack languagePack;

    private LanguagePackContainer() {
        this.languagePack = new LanguagePackDE();
    }

    public static LanguagePack getLanguagePack(){
        if (singleInstance == null){
            singleInstance = new LanguagePackContainer();
        }
        return singleInstance.getSingleInstance();
    }

    public static void setModel(LanguagePack languagePack) {
        if (singleInstance == null){
            singleInstance = new LanguagePackContainer();
        }
        singleInstance.languagePack = languagePack;
    }

    private LanguagePack getSingleInstance() {
        return this.languagePack;
    }

}
