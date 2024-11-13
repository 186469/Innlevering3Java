package no.hvl.dat100.oppgave3;

import no.hvl.dat100.common.TODO;
import no.hvl.dat100.oppgave1.*;

public class Blogg {

	private Innlegg [] innleggstabell;
	private int nesteledig;
	

	public Blogg() {
		
		this.innleggstabell = new Innlegg[20];
		this.nesteledig = 0;
		
	}

	public Blogg(int lengde) {
		this.innleggstabell = new Innlegg[lengde];
		this.nesteledig = 0;
	}

	public int getAntall() {
		return nesteledig;
	}
	
	public Innlegg[] getSamling() {
		return innleggstabell;

	}
	
	public int finnInnlegg(Innlegg innlegg) {

		for (int i = 0; i < nesteledig; i++) {
			if (innleggstabell[i].erLik(innlegg)) {
				return i;
			}
		}
		return -1;
	}

	public boolean finnes(Innlegg innlegg) {
		return finnInnlegg(innlegg) != -1;
	}

	public boolean ledigPlass() {
		return nesteledig < innleggstabell.length;

	}
	
	public boolean leggTil(Innlegg innlegg) {

		if (ledigPlass()) {
			innleggstabell[nesteledig] = innlegg;
			nesteledig++;
			return true;
		}
		return false;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder(nesteledig);
		sb.append(nesteledig).append("\n");
		for (int i = 0; i < nesteledig; i++) {
			sb.append(innleggstabell[i].toString()).append("\n");
		}
		return sb.toString();
	}

	// valgfrie oppgaver nedenfor
	
	public void utvid() {
		
		Innlegg[] nyTabell = new Innlegg[innleggstabell.length * 2];
        for (int i = 0; i < nesteledig; i++) {
            nyTabell[i] = innleggstabell[i];
        }
        innleggstabell = nyTabell;
	}
	
	public boolean leggTilUtvid(Innlegg innlegg) {

		if (!ledigPlass()) {
            utvid();
        }
        innleggstabell[nesteledig] = innlegg;
        nesteledig++;
        return true;
		
	}
	
	public boolean slett(Innlegg innlegg) {
		
		int index = finnInnlegg(innlegg);
        if (index >= 0) {
            innleggstabell[index] = innleggstabell[nesteledig - 1];
            innleggstabell[nesteledig - 1] = null;
            nesteledig--;
            return true;
        }
        return false;
	}
	
	public int[] search(String keyword) {
		
		int[] idTabell = new int[nesteledig];
		int antall = 0;
		for (int i = 0; i < nesteledig; i++) {
			if (innleggstabell[i].toString().contains(keyword)) {
				idTabell[antall] = innleggstabell[i].getId();
				antall++;
			}
		}
		int[] idTabell2 = new int[antall];
		for (int i = 0; i < antall; i++) {
			idTabell2[i] = idTabell[i];
		}
		return idTabell2;

	}
}