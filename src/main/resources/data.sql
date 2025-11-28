-- ============================
-- 1. CRÉATION DE L'AGENCE
-- ============================

INSERT INTO AGENCE (id, date_creation)
VALUES ('A001', CURRENT_DATE);

-- ============================
-- 2. CRÉATION DU GÉRANT
-- ============================

INSERT INTO GERANT (nom, prenom, agence_id)
VALUES ('Durand', 'Paul', 'A001');

-- ============================
-- 3. CRÉATION DU CONSEILLER
-- ============================

INSERT INTO CONSEILLER (nom, prenom, agence_id)
VALUES ('Martin', 'Alice', 'A001');

-- ============================
-- 4. CRÉATION DES CLIENTS
-- ============================

INSERT INTO CLIENT (nom, prenom, adresse, code_postal, ville, telephone, type_client, conseiller_id)
VALUES
    ('Dupont', 'Jean', '1 rue de Paris', '75000', 'Paris', '0102030405', 'PARTICULIER', 1),
    ('Martin', 'Alice', '10 avenue Lyon', '69000', 'Lyon', '0607080910', 'PARTICULIER', 1);

-- ============================
-- 5. CRÉATION DES COMPTES
-- ============================

INSERT INTO COMPTE (numero, solde, date_ouverture, type_compte, client_id)
VALUES
    ('CPT-SRC-001', 500, CURRENT_DATE, 'COURANT', 1),
    ('CPT-DEST-001', 100, CURRENT_DATE, 'COURANT', 2);
