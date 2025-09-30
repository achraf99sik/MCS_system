package enums;

public enum Activite {
    AVOCAT(SecteurActivite.SERVICE),
    MECANICIEN(SecteurActivite.SERVICE),
    AGRICULTEUR(SecteurActivite.AGRICULTURE),
    COMMERCANT(SecteurActivite.COMMERCE),
    MACON(SecteurActivite.CONSTRUCTION);

    private final SecteurActivite secteur;

    Activite(SecteurActivite secteur) {
        this.secteur = secteur;
    }

    public SecteurActivite getSecteur() {
        return secteur;
    }
}
