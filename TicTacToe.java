// TicTacToe.java
// Jeu Morpion 2 joueurs en Java / Tic-Tac-Toe Spiel für 2 Spieler

import java.util.Scanner;

public class TicTacToe {

    // Plateau 3x3 / 3x3 Spielfeld
    static char[][] plateau = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    static char joueurActuel = 'X';   // X commence / X beginnt

    // ── Affichage du plateau / Spielfeld anzeigen ─────────────────────────
    static void afficherPlateau() {
        System.out.println("\n  1   2   3");
        for (int ligne = 0; ligne < 3; ligne++) {
            System.out.print((ligne + 1) + " ");
            for (int col = 0; col < 3; col++) {
                System.out.print(" " + plateau[ligne][col] + " ");
                if (col < 2) System.out.print("|");
            }
            System.out.println();
            if (ligne < 2) System.out.println("  ----+----+----");
        }
        System.out.println();
    }

    // ── Vérifier la victoire / Gewinnprüfung ─────────────────────────────
    static boolean aGagne(char joueur) {
        // Lignes et colonnes / Zeilen und Spalten
        for (int i = 0; i < 3; i++) {
            if (plateau[i][0] == joueur && plateau[i][1] == joueur && plateau[i][2] == joueur)
                return true;
            if (plateau[0][i] == joueur && plateau[1][i] == joueur && plateau[2][i] == joueur)
                return true;
        }
        // Diagonales / Diagonalen
        if (plateau[0][0] == joueur && plateau[1][1] == joueur && plateau[2][2] == joueur)
            return true;
        if (plateau[0][2] == joueur && plateau[1][1] == joueur && plateau[2][0] == joueur)
            return true;
        return false;
    }

    // ── Vérifier le match nul / Unentschieden prüfen ─────────────────────
    static boolean estNul() {
        for (char[] ligne : plateau)
            for (char c : ligne)
                if (c == ' ') return false;
        return true;
    }

    // ── Placer un coup / Zug setzen ───────────────────────────────────────
    static boolean placerCoup(int ligne, int col) {
        if (ligne < 0 || ligne > 2 || col < 0 || col > 2) {
            System.out.println(" Ungültige Position! Werte zwischen 1 und 3 eingeben.");
            return false;
        }
        if (plateau[ligne][col] != ' ') {
            System.out.println(" Dieses Feld ist bereits besetzt!");
            return false;
        }
        plateau[ligne][col] = joueurActuel;
        return true;
    }

    // ── Changer de joueur / Spieler wechseln ─────────────────────────────
    static void changerJoueur() {
        joueurActuel = (joueurActuel == 'X') ? 'O' : 'X';
    }

    // ── Point d'entrée / Hauptmethode ────────────────────────────────────
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║          TIC-TAC-TOE         ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println("Spieler 1 = X  |  Spieler 2 = O");
        System.out.println("Eingabe: Zeile (1-3) dann Spalte (1-3)");

        boolean partieEnCours = true;

        while (partieEnCours) {
            afficherPlateau();
            System.out.printf("Spieler %c ist dran:%n", joueurActuel);

            System.out.print("  Zeile  (1-3): ");
            int ligne = sc.nextInt() - 1;
            System.out.print("  Spalte (1-3): ");
            int col   = sc.nextInt() - 1;

            if (!placerCoup(ligne, col)) continue;   // coup invalide → recommencer

            if (aGagne(joueurActuel)) {
                afficherPlateau();
                System.out.printf(" Spieler %c gewinnt! Herzlichen Glückwunsch!%n", joueurActuel);
                partieEnCours = false;
            } else if (estNul()) {
                afficherPlateau();
                System.out.println(" Unentschieden! Gut gespielt!");
                partieEnCours = false;
            } else {
                changerJoueur();
            }
        }

        sc.close();
    }
}
