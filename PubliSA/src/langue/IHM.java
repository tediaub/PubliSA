package langue;

/**
 * Ensemble des expressions utilises par l'IHM
 * @author Administrateur
 *
 */

public enum IHM {

	NOM_APPLI("NOM_APPLI"),
	ENTETE_FENETRE("ENTETE_FENETRE"),
	
	OK("OK"),
	ANNULER("ANNULER"),
	PARCOURIR("PARCOURIR"),
	SUPPRIMER("SUPPRIMER"),
	
	DETAIL("DETAIL"),
	VERSION("VERSION"),
	MISE_JOUR("MISE_JOUR"),
	CONTACT("CONTACT"),
	SUIVI_VERS("SUIVI_VERS"),
	V3_1("V3_1"),
	TEXT_V3_1("TEXT_V3_1"),
	V3_0("V3_0"),
	TEXT_V3_0("TEXT_V3_0"),
	V2_0("V2_0"),
	TEXT_V2_0("TEXT_V2_0"),
	V1_0("V1_0"),
	TEXT_V1_0("TEXT_V1_0"),
	
	
	FICHIER("FICHIER"),
	NOUVEAU("NOUVEAU"),
	ENREGISTRER("ENREGISTRER"),
	QUITTER("QUITTER"),
	
	REGLAGES("REGLAGES"),
	REG_EMAIL("REG_EMAIL"),
	GEST_UTI("GEST_UTI"),
	FICH_EXT("FICH_EXT"),
	PERSO("PERSO"),
	
	OUTILS("OUTILS"),
	EYDT("EYDT"),
	EXE("EXE"),
	COMPTE_RENDU("COMPTE_RENDU"),
	SUPPR_ENTETE("SUPPR_ENTETE"),
	
	AIDE("AIDE"),
	TUTORIEL("TUTORIEL"),
	LANGUE("LANGUE"),
	L_FR("L_FR"),
	L_EN("L_EN"),
	ABOUT("ABOUT"),
	
	QUITTER_LANGUE("QUITTER_LANGUE"),
	
	BIENVENUE("BIENVENUE"),
	UTI("UTI"),
	CHOISIR_UN_UTI("CHOISIR_UN_UTI"),
	OU_CREER_NOUVEAU("OU_CREER_NOUVEAU"),
	LANCER_LIV("LANCER_LIV"),
	CONTINUER_LIV("CONTINUER_LIV"),
	
	CONTINUER("CONTINUER"),
	OU("OU"),
	CREER("CREER"),
	
	SELECT_LIV("SELECT_LIV"),
	NEW_LIV("NEW_LIV"),
	LIV_UBIK("LIV_UBIK"),
	LIV_THALES("LIV_THALES"),
	SELECT_TYPE_LIV("SELECT_TYPE_LIV"),
	ENTRER_NOM_LIV("ENTRER_NOM_LIV"),
	MES_IDENTITE("MES_IDENTITE"),
	MES_ESPACE("MES_ESPACE"),
	ERREUR_NOM_UTI("ERREUR_NOM_UTI"),
	MES_NOM_UTI("MES_NOM_UTI"),
	MES_SELECT_UTI("MES_SELECT_UTI"),
	ERREUR_UTI("ERREUR_UTI"),
	MES_SELECT_LIV("MES_SELECT_LIV"),
	ERREUR_LIV("ERREUR_LIV"),
	ERREUR_NOM("ERREUR_NOM"),
	ERREUR_TYPE("ERREUR_TYPE"),
	MES_NOM_LIV("MES_NOM_LIV"),
	MES_TYPE_LIV("MES_TYPE_LIV"),	
	MES_QUITTER("MES_QUITTER"),
	MES_NOM_IDENTIQUE_LIV("MES_NOM_IDENTIQUE_LIV"),
	
	EMAIL("EMAIL"),
	MAIL_AKKA_U("MAIL_AKKA_U"),
	MAIL_AKKA_T("MAIL_AKKA_T"),
	MAIL_SOPRA("MAIL_SOPRA"),
	MAIL_THALES("MAIL_THALES"),
	SELECT_PARAM_MAIL("SELECT_PARAM_MAIL"),
	UTI_UNIQUE("UTI_UNIQUE"),
	MODE_MULTI_UTI("MODE_MULTI_UTI"),
	MODE_MULTI_UTI_DESACTIV("MODE_MULTI_UTI_DESACTIV"),
	GEST_LIV("GEST_LIV"),
	SUPPR_LIV_END("SUPPR_LIV_END"),
	SUPPR_ALL_LIV("SUPPR_ALL_LIV"),
	REPERTOIRE_FILECHECK("REPERTOIRE_FILECHECK"),
	PATH_FILECHECK("PATH_FILECHECK"),
	INFO_FILECHECK("INFO_FILECHECK"),
	REPERTOIRE_EYDT("REPERTOIRE_EYDT"),
	PATH_EYDT("PATH_EYDT"),
	INFO_EYDT("INFO_EYDT"),
	CHOIX_FOND("CHOIX_FOND"),
	CHOIX_COULEUR("CHOIX_COULEUR"),
	
	
	CORRESPONDANT("CORRESPONDANT"),
	OBJET("OBJET"),
	MESSAGE("MESSAGE"),
	MARQUEURS("MARQUEURS"),
	
	MES_SUPPR_ALL_LIV("MES_SUPPR_ALL_LIV"),
	
	TEXTE_DCR("TEXTE_DCR"),
	ENTRE_DCR("ENTRE_DCR"),
	TEXTE_PANEL_DCR("TEXTE_PANEL_DCR"),
	TEXTE_PANEL_CALCULATEUR("TEXTE_PANEL_CALCULATEUR"),
	STANDARD("STANDARD"),
	CREATION("CREATION"),
	SEC("SEC"),
	ELAC("ELAC"),
	FCDC("FCDC"),
	
	FICHIER_OGC("FICHIER_OGC"),
	FICHIER_DEZIPPER("FICHIER_DEZIPPER"),
	OUVRIR_OGC("OUVRIR_OGC"),
	PARCOURIR_VERIFIER("PARCOURIR_VERIFIER"),
	RESULTAT_VERIF("RESULTAT_VERIF"),
	BT_COMPTE_RENDU("BT_COMPTE_RENDU"),
	AVANT_ETAPE_SUIV("AVANT_ETAPE_SUIV"),
	BT_SUP_ENTETE("BT_SUP_ENTETE"),
	ETAPE3("ETAPE3"),
	MES_DOC("MES_DOC"),
	MES_VERIF_OK("MES_VERIF_OK"),
	POSITION_ASC_SCH("POSITION_ASC_SCH"),
	NB_ASC_SCH("NB_ASC_SCH"),
	NB_ASC_SCH_SOURCE("NB_ASC_SCH_SOURCE"),
	POSITION_POS("POSITION_POS"),
	NB_POS("NB_POS"),
	NB_POS_SOURCE("NB_POS_SOURCE"),
	
	FICHIER_JOINT("FICHIER_JOINT"),
	APRES_MAIL("APRES_MAIL"),
	A_JOINDRE_UBIK("A_JOINDRE_UBIK"),
	A_JOINDRE_THALES("A_JOINDRE_THALES"),
	
	FICHIER_CIBLE("FICHIER_CIBLE"),
	OUVRIR_OGC_ETAPE4("OUVRIR_OGC_ETAPE4"),
	OUVRIR_CSV("OUVRIR_CSV"),
	VERIFIER("VERIFIER"),
	FIN("FIN"),
	
	MES_ERR_ORIGINAL1("MES_ERR_ORIGINAL1"),
	MES_ERR_ORIGINAL2("MES_ERR_ORIGINAL2"),
	MES_ERR0("MES_ERR0"),
	MES_ERR1("MES_ERR1"),
	MES_ERR2("MES_ERR2"),
	MES_ERR3("MES_ERR3"),
	MES_ERR4("MES_ERR4"),
	MES_ERR5("MES_ERR5"),
	INDICATEUR_ERR("INDICATEUR_ERR"),
	
	DCR("DCR"),
	COL_NOM_PLANCHE("COL_NOM_PLANCHE"),
	COL_NOM_SOMMAIRE("COL_NOM_SOMMAIRE"),
	COL_FICHIER_CSV("COL_FICHIER_CSV"),
	COL_FICHIER_SOMMAIRE("COL_FICHIER_SOMMAIRE"),
	OK_KO("OK_KO"),
	COMMENTAIRES("COMMENTAIRES"),
	
	DETAIL_LIV("DETAIL_LIV"),
	CHANGE_LIV("CHANGE_LIV"),
	CHANGE_LIV_UTI("CHANGE_LIV_UTI"),
	SUPPR_LIV("SUPPR_LIV"),
	SUPPR_UTI("SUPPR_UTI"),
	SUPPR_LIV_QUESTION("SUPPR_LIV_QUESTION"),
	SUPPR_UTI_QUESTION("SUPPR_UTI_QUESTION"),
	SUPPR_LIV_IMPOSSIBLE("SUPPR_LIV_IMPOSSIBLE"),
	
	TEXT_FLECHE1_UBIK("TEXT_FLECHE1_UBIK"),
	TEXT_FLECHE2_UBIK("TEXT_FLECHE2_UBIK"),
	TEXT_FLECHE3_UBIK("TEXT_FLECHE3_UBIK"),
	TEXT_FLECHE4_UBIK("TEXT_FLECHE4_UBIK"),
	TEXT_FLECHE3_THALES("TEXT_FLECHE3_THALES"), 
	
	A_JOINDRE("A_JOINDRE"),
	COMPUTER("COMPUTER"),
	COMPUTER_TYPE("COMPUTER_TYPE"),
	;
	protected String label;

	/** Constructeur */
	IHM(String pLabel) {
		this.label = pLabel;
	}

	public String getLabel() {
		return this.label;
	}   
}

